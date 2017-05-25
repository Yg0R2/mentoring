package com.epam;

import java.util.List;

public class Farm {

	private List<Animal> animals;

	public Farm(List<Animal> animals) {
		this.animals = animals;
	}

	public void sing() {
		for (Animal animal : animals) {
			System.out.println(animal.speak());
		}
	}

}
