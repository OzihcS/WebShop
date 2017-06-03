package repository;

import domain.Order;

import java.sql.Connection;

public interface OrderRepository {

    boolean add(Connection connection, Order order);

}
