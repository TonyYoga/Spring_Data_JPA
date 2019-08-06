package com.telran.telranshopspringdata.data.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "email")
@Table(name = "users")
public class UserEntity {
    @Id
    private String email;
    private String name;
    @Column(unique = true)
    private String phone;
    @Column(precision = 10,scale = 2)
    private BigDecimal balance;
    @OneToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCartEntity shoppingCart;
    @OneToMany(mappedBy = "owner")
    private List<OrderEntity> orders;

    @OneToOne
    @JoinColumn(name = "email")
    private UserDetailsEntity detailsEntity;
}
