package dev.luanfernandes.loja.dao;

import dev.luanfernandes.loja.model.Category;
import dev.luanfernandes.loja.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDAO {

    private final Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Product product) {
        String sql = "INSERT INTO product (name, description) VALUES (?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, product.getName());
            pstm.setString(2, product.getDescription());
            pstm.execute();
            try (ResultSet rst = pstm.getGeneratedKeys()) {
                while (rst.next()) {
                    product.setId(rst.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveWithCategory(Product product) {
        String sql = "INSERT INTO product (name, description, category_id) VALUES (?, ?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, product.getName());
            pstm.setString(2, product.getDescription());
            pstm.setInt(3, product.getCategoryId());
            pstm.execute();
            try (ResultSet rst = pstm.getGeneratedKeys()) {
                while (rst.next()) {
                    product.setId(rst.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> list() {
        List<Product> products = new ArrayList<Product>();
        String sql = "SELECT id, name, description FROM product";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();
            turnResultSetIntoProduct(products, pstm);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public List<Product> find(Category ct) {
        List<Product> products = new ArrayList<Product>();
        String sql = "SELECT ID, name, description FROM product WHERE category_id = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, ct.getId());
            pstm.execute();
            turnResultSetIntoProduct(products, pstm);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public void delete(Integer id) {
        try (PreparedStatement stm = connection.prepareStatement("DELETE FROM product WHERE id = ?")) {
            stm.setInt(1, id);
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String name, String description, Integer id) {
        try (PreparedStatement stm = connection
                .prepareStatement("UPDATE product p SET p.name = ?, p.description = ? WHERE id = ?")) {
            stm.setString(1, name);
            stm.setString(2, description);
            stm.setInt(3, id);
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void turnResultSetIntoProduct(List<Product> products, PreparedStatement pstm) {
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                Product product = new Product(rst.getInt(1), rst.getString(2), rst.getString(3));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
