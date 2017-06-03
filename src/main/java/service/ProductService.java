package service;

import db.DataSourceFactory;
import db.transaction.TransactionHandler;
import domain.Product;
import repository.ProductRepository;
import repository.impl.JDBCProductRepository;

import java.util.List;
import java.util.Map;

public class ProductService {

    private TransactionHandler handler;
    private ProductRepository repository;

    public ProductService(TransactionHandler handler) {
        this.handler = handler;
        this.repository = new JDBCProductRepository();
    }

    /**
     * Returns specific product by id.
     *
     * @param id of product.
     * @return product if exists,
     */
    public Product getById(int id) {
        return handler.doInTransaction(() -> repository.getById(DataSourceFactory.getDataSource().getConnection(), id));
    }

    /**
     * Returns all products from storage.
     *
     * @return {@link List} of products
     */
    public List<Product> getAll() {
        return handler.doInTransaction(() -> repository.getAll(DataSourceFactory.getDataSource().getConnection()));
    }

    /**
     * Returns all products which fit by condition.
     *
     * @param params params for filtration.
     * @return list of products.
     */
    public List<Product> get(Map<String, Object> params) {
        return handler.doInTransaction(() -> repository.get(DataSourceFactory.getDataSource().getConnection(), params));
    }

    /**
     * Counts quantity of products in storage.
     *
     * @return quantity of products
     */
    public int count() {
        return handler.doInTransaction(() -> repository.count(DataSourceFactory.getDataSource().getConnection()));
    }
}
