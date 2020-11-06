package amir.code.gamming.cart.dao;

import amir.code.gamming.cart.dto.CartDto;
import amir.code.gamming.cart.dto.ProductDto;


public interface CartDao{


    CartDto createCart();

    CartDto getCartById(String id);

    void addProduct(String cartId, ProductDto newProduct) throws CartException;

    void removeProduct(String cartId,String productCode) throws CartException;
    
}