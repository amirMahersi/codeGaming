package amir.code.gamming.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import amir.code.gamming.cart.dao.CartDao;
import amir.code.gamming.cart.dao.CartException;
import amir.code.gamming.cart.dto.CartDto;
import amir.code.gamming.cart.dto.ProductDto;


@Component
public class CartServiceImpl implements CartService {

    private final CartDao cartDao;


    @Autowired
    public CartServiceImpl (CartDao cartDao){
        this.cartDao=cartDao;
    }

	@Override
	public CartDto createCart() {
		return cartDao.createCart();
	}

	@Override
	public CartDto getCartById(String id) {
		return cartDao.getCartById(id);
	}

	@Override
	public void removeProduct(String cartId, String productCode) throws CartException {
		cartDao.removeProduct(cartId, productCode);
		
	}

	@Override
	public void addProduct(String cartId, ProductDto newProduct) throws CartException {
		cartDao.addProduct(cartId, newProduct);
	}
    
}