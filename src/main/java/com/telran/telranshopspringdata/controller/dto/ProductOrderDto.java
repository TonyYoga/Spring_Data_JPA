package com.telran.telranshopspringdata.controller.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProductOrderDto {
    private ProductDto product;
    private int count;
}
