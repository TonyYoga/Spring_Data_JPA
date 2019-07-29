package com.telran.telranshopspringdata.controller;


import com.telran.telranshopspringdata.controller.dto.*;
import com.telran.telranshopspringdata.service.Mapper;
import com.telran.telranshopspringdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class UserController {
    @Autowired
    UserService service;

    @PostMapping("user")
    public UserDto addUserInfo(@RequestBody UserDto user) {
        return service.addUserInfo(user.getEmail(), user.getName(), user.getPhone())
                .orElseThrow();
    }

    @GetMapping("user/{userEmail}")
    public UserDto getUserInfo(@PathVariable("userEmail") String userEmail) {
        return service.getUserInfo(userEmail)
                .orElseThrow();
    }

    @GetMapping("products")
    public List<ProductDto> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("categories")
    public List<CategoryDto> getAllCategories() {
        return service.getAllCategories();
    }

    @GetMapping("products/{categoryName}")
    public List<ProductDto> getProductByCategory(@PathVariable("categoryName") String categoryName) {
        return service.getProductsByCategory(categoryName);
    }

    @PostMapping("cart/{userEmail}")
    public ShoppingCartDto addProductToCart(@PathVariable("userEmail") String userEmail,
                                            @RequestBody AddProductDto dto) {
        return service.addProductToCart(userEmail, dto.getProductId(), dto.getCount())
                .orElseThrow();
    }

    @GetMapping("cart/{userEmail}")
    public ShoppingCartDto getShoppingCart(@PathVariable("userEmail") String userEmail) {
        return service.getShoppingCart(userEmail)
                .orElseThrow();
    }

    @DeleteMapping("cart/{userEmail}/{productId}/{count}")
    public ShoppingCartDto removeProductFromCart(@PathVariable("userEmail") String userEmail,
                                                 @PathVariable("productId") String productId,
                                                 @PathVariable("count") int count) {
        return service.removeProductFromCart(userEmail,productId,count)
                .orElseThrow();
    }

    @DeleteMapping("cart/{userEmail}/all")
    public void clearShoppingCart(@PathVariable("userEmail") String userEmail) {
        service.clearShoppingCart(userEmail);
    }

    @GetMapping("orders/{userEmail}")
    public List<OrderDto> getAllOrdersByEmail(@PathVariable("userEmail")String userEmail){
        return service.getOrders(userEmail);
    }


    @GetMapping("checkout/{userEmail}")
    public OrderDto checkout(@PathVariable("userEmail") String userEmail) {
        return service.checkout(userEmail)
                .orElseThrow();
    }
}
