package service;

import db.DataSourceFactory;
import db.transaction.TransactionHandler;
import domain.Order;
import repository.impl.JDBCOrderRepository;

public class OrderService {

    private TransactionHandler handler;
    private JDBCOrderRepository repository;

    public OrderService(TransactionHandler handler) {
        this.handler = handler;
        repository = new JDBCOrderRepository();
    }

    public boolean add(Order order) {
        return handler.doInTransaction(() -> repository.add(DataSourceFactory.getDataSource().getConnection(), order));
    }
}
