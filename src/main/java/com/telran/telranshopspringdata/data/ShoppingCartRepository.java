package com.telran.telranshopspringdata.data;

import com.telran.telranshopspringdata.data.entity.ShoppingCartEntity;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCartEntity, String> {
    ShoppingCartEntity findShoppingCartEntityByOwner_Email(String email);
}
