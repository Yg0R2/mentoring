package com.epam.training;

import java.util.ArrayList;
import java.util.List;

public class Application {

	public Application() {
	}

	public void start() {
		List<Book> books = new ArrayList<>();
		Book book = createBook();
		books.add(book);

		System.out.println(books);
	}

	private Book createBook() {
		Author author = new Author();
		author.setName("Bertrand Meyer");
		author.setDescription("French academic, author, and consultant in the field of computer languages");

		Book book = new Book();
		book.setTitle("Introduction to the Theory of Programming Languages and Touch of Class");
		book.setAuthor(author);
		book.setDescription("This book is an excellent reference for understanding how to architect a language");
		book.setYearOfPublication(1990);

		return book;
	}

	public static void main(String[] args) {
		new Application().start();
	}
}
