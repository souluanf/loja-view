package dev.luanfernandes.loja.model;

public class Product {

	private Integer id;
	private final String name;
	private final String description;
	private Integer categoryId;

	public Product(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Product(Integer id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoryId = categoriaId;
	}

	@Override
	public String toString() {
		return String.format("Product : %d, %s, %s", this.id, this.name, this.description);
	}
}
