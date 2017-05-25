package com.epam;

import java.util.Arrays;
import java.util.List;

public class Application {

	public static void main(String[] args) {
		List<Animal> animals = Arrays.asList(Animal.DOG, Animal.DOG, Animal.DOG, Animal.CAT, Animal.DUCK);

		Farm farm = new Farm(animals);
		farm.sing();
	}

}
