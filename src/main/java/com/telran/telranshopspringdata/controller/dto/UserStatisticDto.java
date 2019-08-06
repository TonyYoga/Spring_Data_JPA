package com.telran.telranshopspringdata.controller.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserStatisticDto {
    private String userEmail;
    private List<ProductOrderDto> products;
    private long totalProductsCount;
    private BigDecimal totalAmount;
}