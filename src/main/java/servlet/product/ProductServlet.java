package servlet.product;

import domain.Product;
import servlet.AbstractServlet;
import util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static util.Constants.Attributes.*;
import static util.Constants.Attributes.Product.*;

/**
 * Displays all products are in storage by specified condition.
 */
@WebServlet(urlPatterns = Constants.ServletPath.PRODUCTS)
public class ProductServlet extends AbstractServlet {

    private static final String DEFAULT_ITEMS_ON_PAGE = String.valueOf(3);
    private static final String DEFAULT_PAGE = String.valueOf(1);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> params = getFilterParams(req);

        List<Product> products = getProductService().get(params);

        req.setAttribute(Constants.Attributes.PRODUCTS, products);
        req.setAttribute(PARAM_LIST, params);
        req.setAttribute(PAGES, (int) Math.ceil(getProductService().count() / Double.valueOf(String.valueOf(params.get(GRID)))));
        req.setAttribute(CURRENT_PAGE, params.get(PAGINATION));
        req.setAttribute(ORDER, params.get(ORDER));

        forward(Constants.Pages.PRODUCTS, req, resp);
    }

    private Map<String, Object> getFilterParams(HttpServletRequest req) {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put(NAME, getStringParam(req, NAME));
        params.put(MANUFACTURER, getStringParam(req, MANUFACTURER));
        params.put(MIN_PRICE, getStringParam(req, MIN_PRICE));
        params.put(MAX_PRICE, getStringParam(req, MAX_PRICE));
        params.put(CATEGORY, req.getParameterValues(CATEGORY));
        params.put(GRID, getStringParam(req, GRID));
        params.put(PAGINATION, getStringParam(req, PAGINATION));
        params.put(ORDER, getStringParam(req, ORDER));

        params.putIfAbsent(GRID, DEFAULT_ITEMS_ON_PAGE);
        params.putIfAbsent(PAGINATION, DEFAULT_PAGE);

        return validateParams(params);
    }

    private Map<String, Object> validateParams(Map<String, Object> params) {
        Map<String, Object> validParams = new LinkedHashMap<>();

        params.forEach((key, value) -> {
            if (value != null && !value.toString().isEmpty()) {
                validParams.put(key, value);
            }
        });

        return validParams;
    }
}
