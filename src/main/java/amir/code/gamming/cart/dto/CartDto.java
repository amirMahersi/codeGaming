package amir.code.gamming.cart.dto;

import java.util.HashSet;
import java.util.Set;

public class CartDto {

    private String id;
    private Double total;
	Set<ProductDto> products; 


	public CartDto(String id) {
		this.id = id;
		this.total = 0.0;
		this.products = new HashSet<>();
	}

	public String getId() {
		return id;
	}
	public void setId(final String id) {
		this.id = id;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(final Double total) {
		this.total = total;
	}
	public Set<ProductDto> getProducts() {
		return products;
	}
	public void setProducts(final Set<ProductDto> products) {
		this.products = products;
	}

    
}