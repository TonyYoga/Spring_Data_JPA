package com.telran.telranshopspringdata.data;

import com.telran.telranshopspringdata.data.entity.ProductOrderEntity;
import com.telran.telranshopspringdata.data.entity.ShoppingCartEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface ProductOrderRepository extends CrudRepository<ProductOrderEntity, String> {
    ProductOrderEntity findProductOrderEntityByProductIdAndShoppingCart(String productId, ShoppingCartEntity shoppingCartEntity);
    void deleteByShoppingCart(ShoppingCartEntity shoppingCartEntity);
    List<ProductOrderEntity> findProductOrderEntitiesByShoppingCart(ShoppingCartEntity shoppingCartEntity);
    List<ProductOrderEntity> findProductOrderEntitiesByCategory_Id(String categotyName);
    default boolean isCategoryNotUsed(String categoryId) {
        var prods = findProductOrderEntitiesByCategory_Id(categoryId);
        if (!findProductOrderEntitiesByCategory_Id(categoryId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Category %s is used in data", categoryId));
        }
        return true;
    }
}
