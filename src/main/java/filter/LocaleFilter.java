package filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Constants;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Array;
import java.util.*;

/**
 * Defines and sets current locale.
 */
public class LocaleFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocaleFilter.class);

    private static final String COOKIES_LOCALE = "lang";

    private List<Locale> locales;
    private Locale defaultLocale;
    private String store;
    private int cookieMaxAge;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        defaultLocale = new Locale(filterConfig.getServletContext().getInitParameter("default_locale"));
        store = filterConfig.getServletContext().getInitParameter("l10n storage");
        locales = new ArrayList<>();

        if (Constants.InitParams.COOKIE.equals(store)) {
            cookieMaxAge = Integer.parseInt(filterConfig.getServletContext().getInitParameter("l10n cookie time"));
        }

        String[] locales = filterConfig.getServletContext().getInitParameter(Constants.Attributes.LOCALES).split(" ");

        for (String l : locales) {
            this.locales.add(new Locale(l));
        }

        if (this.locales.size() == 0) {
            LOGGER.error("Locales loading error, locale are set to default - {}", defaultLocale);
            this.locales = new ArrayList<>();
            this.locales.add(defaultLocale);
        } else {
            defaultLocale = this.locales.get(0);
        }

        filterConfig.getServletContext().setAttribute(Constants.Attributes.LOCALES, locales);
        filterConfig.getServletContext().setAttribute(Constants.Attributes.DEFAULT_LOCALE, defaultLocale);

        LOGGER.debug("Default locale loaded to {}", defaultLocale);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String locale = servletRequest.getParameter(Constants.Attributes.LANG);

        if (locale == null) {
            locale = getLocaleFromSession(request);
        }
        if (locale == null) {
            locale = defineLocaleFromRequest(request);
        }

        if (!locales.contains(new Locale(locale))) {
            locale = defaultLocale.getLanguage();
        }

        LOGGER.info("Locale filter set locale to {}", locale);
        setLocale(request, response, locale);

        chain.doFilter(wrapRequest(request, locale), wrapResponse(response, locale));
    }

    @Override
    public void destroy() {

    }

    private String getLocaleFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = defineLocale(String.valueOf(session.getAttribute(Constants.Attributes.CURRENT_LOCALE)));
        return locale == null ? getLocaleFromCookie(request) : locale;
    }

    private String getLocaleFromCookie(HttpServletRequest request) {
        Cookie cookie = getCookie(request, COOKIES_LOCALE);
        String locale = null;
        if (cookie != null) {
            locale = defineLocale(cookie.getValue());
        }
        return locale == null ? getLocaleFromRequest(request) : locale;
    }

    private String getLocaleFromRequest(HttpServletRequest request) {
        String locale = request.getParameter(Constants.Attributes.CURRENT_LOCALE);
        return locale == null ? defaultLocale.getLanguage() : locale;
    }

    private Cookie getCookie(HttpServletRequest request, String locale) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(locale)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    private String defineLocaleFromRequest(HttpServletRequest request) {
        Enumeration<Locale> locales = request.getLocales();
        while (locales.hasMoreElements()) {
            for (Locale l : this.locales) {
                if (l.getLanguage().equals(locales.nextElement())) {
                    return l.getLanguage();
                }
            }
        }
        return null;
    }

    private String defineLocale(String locale) {
        for (Locale l : this.locales) {
            if (l.equals(locale)) {
                return l.getLanguage();
            }
        }
        return null;
    }

    private void setLocale(HttpServletRequest request, HttpServletResponse response, String locale) {
        request.setAttribute(Constants.Attributes.CURRENT_LOCALE, locale);
        request.setAttribute(Constants.Attributes.LOCALES, locales);
        if (Constants.InitParams.COOKIE.equals(store)) {
            setLocaleToCookie(response, locale);
        } else if (Constants.InitParams.SESSION.equals(store)) {
            setLocaleToSession(request, locale);
        }
    }

    private void setLocaleToCookie(HttpServletResponse response, String locale) {
        Cookie cookie = new Cookie(COOKIES_LOCALE, locale);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(cookieMaxAge);
        response.addCookie(cookie);
    }

    private void setLocaleToSession(HttpServletRequest request, String locale) {
        HttpSession session = request.getSession();
        session.setAttribute(Constants.Attributes.CURRENT_LOCALE, locale);
    }

    private HttpServletRequest wrapRequest(HttpServletRequest request, String locale) {
        request = new HttpServletRequestWrapper(request) {
            @Override
            public Locale getLocale() {
                return new Locale(locale);
            }

            @Override
            public Enumeration<Locale> getLocales() {
                return Collections.enumeration(locales);
            }
        };
        return request;
    }

    private HttpServletResponse wrapResponse(HttpServletResponse response, String locale) {
        response = new HttpServletResponseWrapper(response) {
            @Override
            public Locale getLocale() {
                return new Locale(locale);
            }
        };
        return response;
    }
}
