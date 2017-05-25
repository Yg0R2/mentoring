package com.epam.training;

public class Book {

	private Author author;

	private int yearOfPublication;

	private String title;

	private String description;

	public Book() {
		super();
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public int getYearOfPublication() {
		return yearOfPublication;
	}

	public void setYearOfPublication(int yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Book [" + author.toString() + ", yearOfPublication=" + yearOfPublication +
			", title=" + title + ", description=" + description + "]";
	}

}
