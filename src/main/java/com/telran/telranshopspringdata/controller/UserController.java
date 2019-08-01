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
    public UserDto addUserInfo(@RequestBody UserDto user) {
        return userService.addUserInfo(user.getEmail(), user.getName(), user.getPhone())
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

    @PostMapping("cart/{userEmail}")
    public ShoppingCartDto addProductToCart(@PathVariable("userEmail") String userEmail,
                                            @RequestBody AddProductDto dto) {
        return userService.addProductToCart(userEmail, dto.getProductId(), dto.getCount())
                .orElseThrow();
    }

    @GetMapping("cart/{userEmail}")
    public ShoppingCartDto getShoppingCart(@PathVariable("userEmail") String userEmail) {
        return userService.getShoppingCart(userEmail)
                .orElseThrow();
    }

    @DeleteMapping("cart/{userEmail}/{productId}/{count}")
    public ShoppingCartDto removeProductFromCart(@PathVariable("userEmail") String userEmail,
                                                 @PathVariable("productId") String productId,
                                                 @PathVariable("count") int count) {
        return userService.removeProductFromCart(userEmail,productId,count)
                .orElseThrow();
    }

    @DeleteMapping("cart/{userEmail}/all")
    public void clearShoppingCart(@PathVariable("userEmail") String userEmail) {
        userService.clearShoppingCart(userEmail);
    }

    @GetMapping("orders/{userEmail}")
    public List<OrderDto> getAllOrdersByEmail(@PathVariable("userEmail")String userEmail){
        return userService.getOrders(userEmail);
    }


    @GetMapping("checkout/{userEmail}")
    public OrderDto checkout(@PathVariable("userEmail") String userEmail) {
        return userService.checkout(userEmail)
                .orElseThrow();
    }
}
