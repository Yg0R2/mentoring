package com.epam;

import com.epam.data.MemoryDataStore;
import com.epam.price.DiscountPriceCalculator;
import com.epam.price.ExclusivePriceCalculator;
import com.epam.store.Store;

public class Application {

	public void startDefault() {
		System.out.println("Default store: ");
		Store store = new Store(new DiscountPriceCalculator(), new MemoryDataStore<Product>());
		store.open();
		store.printPrices();
		System.out.println("Stock: " + store.stock());
	}

	public void startExclusive() {
		System.out.println("Exclusive store: ");
		Store store = new Store(new ExclusivePriceCalculator(), new MemoryDataStore<Product>());
		store.open();
		store.printPrices();
		System.out.println("Stock: " + store.stock());
	}

	public static void main(String[] args) {
		new Application().startDefault();
		System.out.println();
		new Application().startExclusive();
	}
}
