package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_category;
	private String name_category;
	
	@JsonManagedReference(value="product-category")
	@OneToMany(mappedBy="category",cascade= CascadeType.REMOVE)
	private List<Product> products;
	
	
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(int id_category, String name_category, List<Product> products) {
		super();
		this.id_category = id_category;
		this.name_category = name_category;
		this.products = products;
	}
	public int getId_category() {
		return id_category;
	}
	public void setId_category(int id_category) {
		this.id_category = id_category;
	}
	public String getName_category() {
		return name_category;
	}
	public void setName_category(String name_category) {
		this.name_category = name_category;
	}
	@Override
	public String toString() {
		return "Category [id_category=" + id_category + ", name_category=" + name_category + ", products=" + products
				+ "]";
	}
		
}
