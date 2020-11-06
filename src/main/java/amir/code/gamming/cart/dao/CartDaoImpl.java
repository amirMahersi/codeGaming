package amir.code.gamming.cart.dao;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import amir.code.gamming.cart.dto.CartDto;
import amir.code.gamming.cart.dto.ProductDto;


@Component
public class CartDaoImpl implements CartDao{

	private static Map<String,CartDto> CART_DB = new ConcurrentHashMap<String,CartDto>();

	@Override
	public CartDto createCart() {

		CartDto newCart = new CartDto(UUID.randomUUID().toString());
		
		CART_DB.put(newCart.getId(), newCart);
		
		return newCart;
	}

	@Override
	public CartDto getCartById(final String id) {
		return CART_DB.get(id);
	}

	@Override
	public void addProduct(final String cartId, final ProductDto newProduct) throws  CartException{
		CartDto cart = CART_DB.get(cartId);
		if(cart == null){
           throw new CartException();
		}
		if(!cart.getProducts().add(newProduct)){
		cart.getProducts().remove(newProduct);
		cart.getProducts().add(newProduct);
		}
		cart.setTotal(cart.getTotal()
		+ newProduct.getPrice()
		* newProduct.getQuantity());
	}

	@Override
	public void removeProduct(final String cartId, final String productCode) throws CartException {
		CartDto cart = CART_DB.get(cartId);
		if(cart == null){
           throw new CartException();
		}

		Optional<ProductDto> productToRemove = cart.getProducts()
		.stream()
		.filter(product -> product.getCode().equalsIgnoreCase(productCode))
		.findFirst();
		 
		if (productToRemove.isPresent()){
			 cart.getProducts().remove(productToRemove.get());
			 cart.setTotal(cart.getTotal()
			 - productToRemove.get().getPrice()
			 * productToRemove.get().getQuantity());
		}
		else 
		{
			throw new CartException();
		}

	}
    
}