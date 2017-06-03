package servlet.product;

import domain.Product;
import servlet.AbstractServlet;
import util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Gives information about specific product.
 */
@WebServlet(urlPatterns = Constants.ServletPath.SINGLE)
public class ProductInfoServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = getStringParam(req, "product");
        Product product = getProductService().getById(Integer.parseInt(id));
        req.setAttribute("product", product);
        forward(Constants.Pages.SINGLE, req, resp);
    }
}
