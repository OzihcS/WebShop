package servlet;

import domain.Order;
import domain.OrderStatus;
import domain.User;
import util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = Constants.ServletPath.MAKE_ORDER)
public class MakeOrderServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(Constants.Pages.ORDER, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(Constants.Attributes.CURRENT_USER);
        String payment = getStringParam(req, "payment");
        String requisites = getStringParam(req, "requisites");

        Order order = new Order();
        order.setCart(getCartService().getCart());
        order.setStatus(OrderStatus.ACCEPTED);
        order.setUser(user);
        order.setDate(new Date());

        if (getOrderService().add(order)) {
            getCartService().clear();
        } else {
            resp.sendError(500);
        }
    }
}
