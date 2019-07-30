package com.telran.telranshopspringdata.data;

import com.telran.telranshopspringdata.data.entity.ProductOrderEntity;
import com.telran.telranshopspringdata.data.entity.ShoppingCartEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductOrderRepository extends CrudRepository<ProductOrderEntity, String> {
    ProductOrderEntity findProductOrderEntityByProductIdAndShoppingCart(String productId, ShoppingCartEntity shoppingCartEntity);
    void deleteByShoppingCart(ShoppingCartEntity shoppingCartEntity);
    List<ProductOrderEntity> findProductOrderEntitiesByShoppingCart(ShoppingCartEntity shoppingCartEntity);
}
