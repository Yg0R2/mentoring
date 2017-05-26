package com.epam.price;

import java.math.BigDecimal;

import com.epam.Product;

public class ExclusivePriceCalculator implements PriceCalculator {

	@Override
	public BigDecimal price(Product product) {
		return product.getPrice().multiply(new BigDecimal("2"));
	}

}
