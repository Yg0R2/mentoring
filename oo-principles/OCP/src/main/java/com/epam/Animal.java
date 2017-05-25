package com.epam;

public enum Animal {
	DOG("Woof!"), CAT("Meow!"), DUCK("Quack!");

	private final String say;

	Animal(String say) {
		this.say = say;
	}

	public String speak() {
		return say;
	}

}
