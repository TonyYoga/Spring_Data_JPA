package com.telran.telranshopspringdata.data.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="product_order")
public class ProductOrderEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String productId;
    private String name;
    private int count;
    private BigDecimal price;
    @OneToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    @ManyToOne
    @JoinColumn(name = "shoping_cart_id")
    private ShoppingCartEntity shoppingCart;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
