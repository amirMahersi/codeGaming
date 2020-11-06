package amir.code.gamming.cart;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import amir.code.gamming.cart.dao.CartException;
import amir.code.gamming.cart.dto.CartDto;
import amir.code.gamming.cart.dto.ProductDto;
import amir.code.gamming.cart.service.CartService;


@RestController
@RequestMapping(value="/v1.0/carts")
public class CartControllerV1 {
    
    private final CartService cartService;


    @Autowired
    public CartControllerV1(CartService cartService){
         this.cartService=cartService;
    }

    @PostMapping
    public CartDto createCart() {
        return cartService.createCart();
    }

    @GetMapping(value = "/{cartId}")
    public CartDto getOneCart(@PathVariable("cartId") String id) {
        CartDto cart = cartService.getCartById(id);
        if(cart == null){
            throw new ResponseStatusException(
           HttpStatus.NOT_FOUND, "cart Not Found");
        }
        return cart;
    }

    @PostMapping(value="/{cartId}/products")
    @ResponseStatus( HttpStatus.CREATED )
    public void addProduct(
        @PathVariable("cartId") String cartId,
        @RequestBody ProductDto newProduct) {
        try {
			cartService.addProduct(cartId, newProduct);
		} catch (CartException e) {
			throw new ResponseStatusException(
           HttpStatus.NOT_FOUND, "ressource Not Found", e);
		}
    }

    @DeleteMapping(value="/{cartId}/products/{productCode}")
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void removeProduct(
        @PathVariable("cartId") String cartId,
        @PathVariable("productCode") String productCode) {
        try {
			cartService.removeProduct(cartId, productCode);
		} catch (CartException e) {
			throw new ResponseStatusException(
           HttpStatus.NOT_FOUND, "ressource Not Found", e);
		}
    }




}