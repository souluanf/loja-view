package dev.luanfernandes.loja.dao;

import dev.luanfernandes.loja.model.Category;
import dev.luanfernandes.loja.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CategoryDAO {

    private final Connection connection;

    public CategoryDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Category> list() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT id, name FROM category";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();
            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    Category category = new Category(rst.getInt(1), rst.getString(2));
                    categories.add(category);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    public List<Category> listWithProduct() {
        Category lastCategory = null;
        List<Category> categories = new ArrayList<>();

        String sql = "SELECT c.id, c.name, p.id, p.name, p.description FROM category c INNER JOIN product p ON c.id = p.category_id";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();

            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    if (lastCategory == null || !lastCategory.getName().equals(rst.getString(2))) {
                        Category category = new Category(rst.getInt(1), rst.getString(2));

                        categories.add(category);
                        lastCategory = category;
                    }
                    Product product = new Product(rst.getInt(3), rst.getString(4), rst.getString(5));
                    lastCategory.addProduct(product);
                }
            }
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
