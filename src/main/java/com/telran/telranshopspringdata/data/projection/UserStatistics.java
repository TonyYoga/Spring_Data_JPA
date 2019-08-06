package com.telran.telranshopspringdata.data.projection;

import java.math.BigDecimal;

public interface UserStatistics {
    String getUserEmail();
    long getTotalProductsCount();
    BigDecimal getTotalAmount();
}
