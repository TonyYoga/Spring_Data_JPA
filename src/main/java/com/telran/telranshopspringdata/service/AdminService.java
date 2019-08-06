package com.telran.telranshopspringdata.service;

import com.telran.telranshopspringdata.controller.dto.ProductStatisticDto;
import com.telran.telranshopspringdata.controller.dto.UserStatisticDto;

import java.math.BigDecimal;
import java.util.List;

public interface AdminService {
    String addCategory(String categoryName);
    String addProduct(String productName, BigDecimal price, String categoryId);
    boolean removeProduct(String productId);
    boolean removeCategory(String categoryId);
    boolean updateCategory(String categoryId, String categoryName);
    boolean changeProductPrice(String productId,BigDecimal price);
    boolean addBalance(String userEmail, BigDecimal balance);
    List<ProductStatisticDto> getMostPopularProduct();
    List<ProductStatisticDto> getMostProfitableProduct();
    List<UserStatisticDto> getMostActiveUser();
    List<UserStatisticDto> getMostProfitableUser();
}
