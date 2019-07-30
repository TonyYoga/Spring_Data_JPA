package com.telran.telranshopspringdata.data;

import com.telran.telranshopspringdata.data.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity, String> {
    List<OrderEntity> findByOwner_Email(String userEmail);
}
