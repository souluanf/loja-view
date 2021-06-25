package dev.luanfernandes.loja.model;

import java.util.ArrayList;
import java.util.List;

public class Category {

	private final Integer id;
	private final String name;
	private final List<Product> products = new ArrayList<Product>();

	public Category(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void addProduct(Product product) {
		products.add(product);
	}

	public List<Product> getProducts() {
		return products;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
