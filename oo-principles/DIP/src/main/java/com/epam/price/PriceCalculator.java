package com.epam.price;

import com.epam.Product;

import java.math.BigDecimal;

public interface PriceCalculator {

    BigDecimal price(Product product);

}
