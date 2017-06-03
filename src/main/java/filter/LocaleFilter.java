package filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Constants;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

/**
 * Defines and sets current locale.
 */
public class LocaleFilter extends BaseFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocaleFilter.class);

    private static final String COOKIES_LOCALE = "lang";

    private String[] locales;
    private String defaultLocale;
    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        loadLocales(filterConfig);
    }

    /**
     * Loads locales from {@link ServletContext}
     *
     * @param filterConfig {@link FilterConfig} locales to be loaded from.
     */
    private void loadLocales(FilterConfig filterConfig) {
        defaultLocale = filterConfig.getServletContext().getInitParameter("default_locale");
        locales = filterConfig.getServletContext().getInitParameter(Constants.Attributes.LOCALES).split(" ");

        if (locales.length == 0) {
            LOGGER.error("Locales loading error, locale are set to default - {}", defaultLocale);
            locales = new String[]{defaultLocale};
        } else {
            defaultLocale = locales[0];
        }
        context = filterConfig.getServletContext();
        context.setAttribute(Constants.Attributes.LOCALES, locales);
        context.setAttribute(Constants.Attributes.DEFAULT_LOCALE, defaultLocale);

        LOGGER.debug("Default locale loaded to {}", defaultLocale);
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String locale = request.getParameter(Constants.Attributes.LANG);

        if (locale == null) {
            locale = getLocaleFromSession(request);
        }

        if (locale == null) {
            locale = defineLocaleFromRequest(request);
        }
        LOGGER.info("Locale filter set locale to {}", locale);
        setLocale(request, response, locale);

        chain.doFilter(wrapRequest(request, locale), wrapResponse(response, locale));
    }

    private String getLocaleFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = defineLocale(String.valueOf(session.getAttribute(Constants.Attributes.CURRENT_LOCALE)));
        return locale == null ? getLocaleFromCookie(request) : locale;
    }

    private String getLocaleFromCookie(HttpServletRequest request) {
        Cookie cookie = getCookie(request, COOKIES_LOCALE);
        String locale = defineLocale(cookie != null ? cookie.getValue() : null);
        return locale == null ? getLocaleFromRequest(request) : locale;
    }

    private String getLocaleFromRequest(HttpServletRequest request) {
        String locale = request.getParameter(Constants.Attributes.CURRENT_LOCALE);
        return locale == null ? defaultLocale : locale;
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
            for (String l : this.locales) {
                if (l.equals(locales.nextElement())) {
                    return l;
                }
            }
        }
        return null;
    }

    private String defineLocale(String locale) {
        for (String l : locales) {
            if (l.equals(locale)) {
                return l;
            }
        }
        return null;
    }

    private void setLocale(HttpServletRequest request, HttpServletResponse response, String locale) {
        request.setAttribute(Constants.Attributes.CURRENT_LOCALE, locale);
        setLocaleToCookie(response, locale);
        setLocaleToSession(request, locale);
    }

    private void setLocaleToCookie(HttpServletResponse response, String locale) {
        Cookie cookie = new Cookie(COOKIES_LOCALE, locale);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(Integer.MAX_VALUE);
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
                List<Locale> localesList = new ArrayList<>();

                for (String locale : locales) {
                    localesList.add(new Locale(locale));
                }

                return Collections.enumeration(localesList);
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
