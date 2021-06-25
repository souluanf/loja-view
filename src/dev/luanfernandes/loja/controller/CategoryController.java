package dev.luanfernandes.loja.controller;

import dev.luanfernandes.loja.dao.CategoryDAO;
import dev.luanfernandes.loja.factory.ConnectionFactory;
import dev.luanfernandes.loja.model.Category;

import java.sql.Connection;
import java.util.List;

public class CategoryController {

    private final CategoryDAO categoryDAO;

    public CategoryController(){
        Connection connection = new ConnectionFactory().getConnection();
        this.categoryDAO = new CategoryDAO(connection);
    }

    public List<Category> list() {
        return this.categoryDAO.list();
    }
}
