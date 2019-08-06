package com.telran.telranshopspringdata.data;

import com.telran.telranshopspringdata.data.entity.OrderEntity;
import com.telran.telranshopspringdata.data.projection.UserStatistics;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Stream;

public interface OrderRepository extends CrudRepository<OrderEntity, String> {
    List<OrderEntity> findByOwner_Email(String userEmail);

    @Query(value = "SELECT u.email AS userEmail, SUM(po.count) AS totalProductsCount, SUM(po.count * po.price) AS totalAmount\n" +
            "FROM orders o\n" +
            "         JOIN users u on o.owner_id = u.email\n" +
            "         JOIN product_order po on o.id = po.order_id\n" +
            "GROUP BY u.email \n" +
            "ORDER BY totalProductsCount DESC ", nativeQuery = true)
    Stream<UserStatistics> getMostActiveUser();

    @Query(value = "SELECT u.email AS userEmail, SUM(po.count) AS totalProductsCount, SUM(po.count * po.price) AS totalAmount\n" +
            "FROM orders o\n" +
            "         JOIN users u on o.owner_id = u.email\n" +
            "         JOIN product_order po on o.id = po.order_id\n" +
            "GROUP BY u.email \n" +
            "ORDER BY totalAmount DESC", nativeQuery = true)
    Stream<UserStatistics> getMostProfitableUser();
}
