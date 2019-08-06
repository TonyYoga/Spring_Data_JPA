package com.telran.telranshopspringdata.data;

import com.telran.telranshopspringdata.data.entity.ProductOrderEntity;
import com.telran.telranshopspringdata.data.entity.ShoppingCartEntity;
import com.telran.telranshopspringdata.data.projection.ProductStatistics;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Stream;

public interface ProductOrderRepository extends CrudRepository<ProductOrderEntity, String> {
    ProductOrderEntity findProductOrderEntityByProductIdAndShoppingCart(String productId, ShoppingCartEntity shoppingCartEntity);
    void deleteByShoppingCart(ShoppingCartEntity shoppingCartEntity);
    List<ProductOrderEntity> findProductOrderEntitiesByShoppingCart(ShoppingCartEntity shoppingCartEntity);
    List<ProductOrderEntity> findProductOrderEntitiesByCategory_Id(String categoryName);
    Stream<ProductOrderEntity> findByOrder_Owner(String email);
    Stream<ProductOrderEntity> findByOrder_Owner_Email(String email);
    default boolean isCategoryNotUsed(String categoryId) {
        var prods = findProductOrderEntitiesByCategory_Id(categoryId);
        if (!findProductOrderEntitiesByCategory_Id(categoryId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Category %s is used in data", categoryId));
        }
        return true;
    }

    @Query(value = "SELECT p.name AS productName, " +
            "c.name AS productCategory, " +
            "SUM(p.count) AS numberOfPurchases, " +
            "SUM(p.price * p.count) AS totalAmount " +
            "FROM product_order p " +
            "LEFT JOIN categories c on p.category_id = c.id " +
            "WHERE shoping_cart_id IS null " +
            "GROUP BY p.name, category_id " +
            "ORDER BY numberOfPurchases DESC" , nativeQuery = true)
    Stream<ProductStatistics> getPopularProductStatistics();

    @Query(value = "SELECT p.name AS productName, " +
            "c.name AS productCategory, " +
            "SUM(p.count) AS numberOfPurchases, " +
            "SUM(p.price * p.count) AS totalAmount " +
            "FROM product_order p " +
            "LEFT JOIN categories c on p.category_id = c.id " +
            "WHERE shoping_cart_id IS null " +
            "GROUP BY p.name, category_id " +
            "ORDER BY totalAmount DESC" , nativeQuery = true)
    Stream<ProductStatistics> getProfitableProductStatistics();
}
