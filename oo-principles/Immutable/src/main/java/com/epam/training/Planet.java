package com.epam.training;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Planet {

	private final String name;
	private final LocalDate dateOfDiscovery;
	private final List<Moon> moons;

	public Planet(String name, LocalDate dateOfDiscovery, List<Moon> moons) {
		this.name = name;
		this.dateOfDiscovery = dateOfDiscovery;
		this.moons = new ArrayList<>(moons);
	}

	public String getName() {
		return name;
	}

	public LocalDate getDateOfDiscovery() {
		return dateOfDiscovery;
	}

	public List<Moon> getMoons() {
		return moons;
	}

}
