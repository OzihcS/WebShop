package service;

import domain.Cart;
import domain.Product;

import java.util.Map;

public class CartService {

    private Cart cart;

    public CartService() {
        this.cart = new Cart();
    }

    public void add(Product product) {
        cart.add(product);
    }

    public Map<Product, Integer> getAll() {
        return cart.getCart();
    }

    public void clear() {
        cart.clear();
    }

    public void remove(Product product) {
        cart.remove(product);
    }

    public Cart getCart() {
        return cart;
    }
}
