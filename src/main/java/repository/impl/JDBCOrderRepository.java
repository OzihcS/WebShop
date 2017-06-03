package repository.impl;

import db.Query;
import domain.Cart;
import domain.Order;
import exception.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.OrderRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCOrderRepository implements OrderRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCOrderRepository.class);
    private static final String ERROR_MESSAGE = "Cannot handle sql ['{}']; Message: ";

    private static final String ADD = "order.add";
    private static final String ADD_CART = "cart.add";

    @Override
    public boolean add(Connection connection, Order order) {
        String sql = Query.get(ADD);
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            int k = 1;
            preparedStatement.setString(k++, order.getStatus().name());
            preparedStatement.setString(k++, order.getStatusDetailing());
            preparedStatement.setDate(k++, new Date(order.getDate().getTime()));
            preparedStatement.setInt(k++, order.getUser().getId());

            return preparedStatement.executeUpdate() > 0 && addOrderProduct(connection, order.getCart(), order.getId());
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    private boolean addOrderProduct(Connection connection, Cart cart, int order_id) {
        String sql = Query.get(ADD_CART);
        final boolean[] result = new boolean[1];
        cart.getCart().forEach((product, quantity) -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                int k = 1;
                preparedStatement.setInt(k++, order_id);
                preparedStatement.setInt(k++, product.getId());
                preparedStatement.setInt(k++, quantity);

                if (preparedStatement.executeUpdate() > 0) {
                    result[0] = true;
                }

            } catch (SQLException e) {
                LOGGER.warn(ERROR_MESSAGE, sql, e);
                throw new DataAccessException(e.getMessage(), e);
            }
        });
        return result[0];
    }
}
