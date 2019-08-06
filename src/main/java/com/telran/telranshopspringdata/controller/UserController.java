package com.telran.telranshopspringdata.controller;


import com.telran.telranshopspringdata.controller.dto.*;
import com.telran.telranshopspringdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("user")
    public UserDto addUserInfo(@RequestBody UserDto user, Principal principal) {
        return userService.addUserInfo(principal.getName(), user.getName(), user.getPhone())
                .orElseThrow();
    }

    @GetMapping("user")
    public UserDto getUserInfo(Principal principal) {
        return userService.getUserInfo(principal.getName())
                .orElseThrow();
    }

    @GetMapping("products")
    public List<ProductDto> getAllProducts() {
        return userService.getAllProducts();
    }

    @GetMapping("categories")
    public List<CategoryDto> getAllCategories() {
        return userService.getAllCategories();
    }

    @GetMapping("products/{categoryName}")
    public List<ProductDto> getProductByCategory(@PathVariable("categoryName") String categoryName) {
        return userService.getProductsByCategory(categoryName);
    }

    @PostMapping("cart")
    public ShoppingCartDto addProductToCart(@RequestBody AddProductDto dto, Principal principal) {
        return userService.addProductToCart(principal.getName(), dto.getProductId(), dto.getCount())
                .orElseThrow();
    }

    @GetMapping("cart")
    public ShoppingCartDto getShoppingCart(Principal principal) {
        return userService.getShoppingCart(principal.getName())
                .orElseThrow();
    }

    @DeleteMapping("cart/{productId}/{count}")
    public ShoppingCartDto removeProductFromCart(@PathVariable("productId") String productId,
                                                 @PathVariable("count") int count,
                                                 Principal principal) {
        return userService.removeProductFromCart(principal.getName(),productId,count)
                .orElseThrow();
    }

    @DeleteMapping("cart/all")
    public void clearShoppingCart(Principal principal) {
        userService.clearShoppingCart(principal.getName());
    }

    @GetMapping("orders")
    public List<OrderDto> getAllOrdersByEmail(Principal principal){
        return userService.getOrders(principal.getName());
    }


    @GetMapping("checkout")
    public OrderDto checkout(Principal principal) {
        return userService.checkout(principal.getName())
                .orElseThrow();
    }
}
