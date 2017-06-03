package servlet;

import util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@WebServlet(urlPatterns = "/language")
public class LocaleServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("!!!!");
        String newLocale = getStringParam(req, Constants.Attributes.LANG);

        System.out.println(newLocale);

        if (Collections.singletonList(req.getLocales()).contains(newLocale)) {
            req.getRequestDispatcher("/lang/" + newLocale + ".json").forward(req, resp);
        }
    }
}
