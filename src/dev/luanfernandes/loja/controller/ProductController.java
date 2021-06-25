package dev.luanfernandes.loja.controller;

import dev.luanfernandes.loja.dao.ProductDAO;
import dev.luanfernandes.loja.factory.ConnectionFactory;
import dev.luanfernandes.loja.model.Product;

import java.sql.Connection;
import java.util.List;

public class ProductController {

    private final ProductDAO productDAO;

    public ProductController() {
        Connection connection = new ConnectionFactory().getConnection();
        this.productDAO = new ProductDAO(connection);
    }

    public void delete(Integer id) {
        this.productDAO.delete(id);
    }

    public void save(Product product) {
        this.productDAO.save(product);
    }

    public List<Product> list() {
        return this.productDAO.list();
    }

    public void update(String name, String description, Integer id) {
        this.productDAO.update(name, description, id);
    }
}
