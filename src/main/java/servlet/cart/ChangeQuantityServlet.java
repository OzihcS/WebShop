package servlet.cart;

import domain.Product;
import servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/changeQuantity")
public class ChangeQuantityServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = getStringParam(req, "item_id");
        String quantity = getStringParam(req, "quantity");
        Product product = getProductService().getById(Integer.parseInt(id));
        getCartService().getAll().put(product, Integer.valueOf(quantity));
    }
}
