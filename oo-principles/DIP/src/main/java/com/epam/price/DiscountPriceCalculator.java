package com.epam.price;

import java.math.BigDecimal;

import com.epam.Product;

public class DiscountPriceCalculator implements PriceCalculator {

	@Override
	public BigDecimal price(Product product) {
		return product.getPrice().multiply(new BigDecimal("0.9"));
	}

}
