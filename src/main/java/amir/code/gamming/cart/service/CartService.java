package amir.code.gamming.cart.service;

import amir.code.gamming.cart.dao.CartException;
import amir.code.gamming.cart.dto.CartDto;
import amir.code.gamming.cart.dto.ProductDto;

public interface CartService {


    CartDto createCart();

    CartDto getCartById(String id);

    void addProduct(String cartId, ProductDto newProduct) throws CartException;

    void removeProduct(String cartId, String codeProduct) throws CartException;
    
}