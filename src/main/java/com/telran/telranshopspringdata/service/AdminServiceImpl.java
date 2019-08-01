package com.telran.telranshopspringdata.service;

import com.telran.telranshopspringdata.data.CategoryRepository;
import com.telran.telranshopspringdata.data.ProductOrderRepository;
import com.telran.telranshopspringdata.data.ProductRepository;
import com.telran.telranshopspringdata.data.UserRepository;
import com.telran.telranshopspringdata.data.entity.CategoryEntity;
import com.telran.telranshopspringdata.data.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class AdminServiceImpl implements AdminService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductOrderRepository productOrderRepository;


    @Override
    public String addCategory(String categoryName) {
        if (!categoryRepository.existsById(categoryName)) {
            return categoryRepository.save(CategoryEntity.builder()
                    .name(categoryName)
                    .build()
            ).getId();
        }
        return null;
    }

    @Override
    public String addProduct(String productName, BigDecimal price, String categoryId) {
        Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new RuntimeException(String.format("No such category with id %s",categoryId));
        }
        return productRepository.save(ProductEntity.builder()
                .category(category.get())
                .name(productName)
                .price(price)
                .build()
        ).getId();
    }

    @Override
    public boolean removeProduct(String productId) {
        if (!productRepository.existsById(productId)) {
            return false;
        }
        productRepository.deleteById(productId);
        return true;
    }

    @Override
    public boolean removeCategory(String categoryId) {
        productOrderRepository.isCategoryNotUsed(categoryId);
        if (!categoryRepository.existsById(categoryId)) {
            return false;
        }
        categoryRepository.deleteById(categoryId);
        return true;
    }

    @Override
    public boolean updateCategory(String categoryId, String categoryName) {
        Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new RuntimeException(String.format("Category with id %s not found!", categoryId));
        }
        category.get().setName(categoryName);
        return true;
    }

    @Override
    public boolean changeProductPrice(String productId, BigDecimal price) {
        Optional<ProductEntity> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new RuntimeException(String.format("Product %s not found", productId));
        }
        product.get().setPrice(price);
        return true;
    }

    @Override
    public boolean addBalance(String userEmail, BigDecimal balance) {
        var user = userRepository.findById(userEmail);
        if (user.isEmpty()) {
            throw new RuntimeException(String.format("User %s not found", userEmail));
        }
        user.get().setBalance(balance);
        return true;
    }
}
