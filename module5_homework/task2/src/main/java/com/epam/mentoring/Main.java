package com.epam.mentoring;

import com.epam.mentoring.author.Author;
import com.epam.mentoring.book.Book;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Supplier<Stream<Book>> bookSupplier = () -> getBookSupplier();

        Main instance = new Main();

        instance.booksWithMoreThan200Pages(bookSupplier.get());
        instance.bookWithHighestPageNumber(bookSupplier.get());
        instance.bookWithLowestPageNumber(bookSupplier.get());
        instance.booksWithSingleAuthor(bookSupplier.get());
        instance.printBooksWithForEach(bookSupplier.get());
        instance.listAuthors(bookSupplier.get());
    }

    private static Stream<Book> getBookSupplier() {
        Author author1 = new Author("Author1 name", (short) 34);
        Author author2 = new Author("Author2 name", (short) 30);
        Author author3 = new Author("Author3 name", (short) 25);

        return Stream.of(
            new Book("Book 1", Arrays.asList(author1), 110),
            new Book("Book 2", Arrays.asList(author2), 210),
            new Book("Book 3", Arrays.asList(author1, author2), 400),
            new Book("Book 4", Arrays.asList(author3), 500),
            new Book("Book 5", Arrays.asList(author1, author2, author3), 100)
        );
    }

    private static String joinStream(Stream<String> stream) {
        return stream.collect(Collectors.joining(", "));
    }

    private void booksWithMoreThan200Pages(Stream<Book> bookStream) {
        List<String> books = bookStream
            .filter(book -> book.getNumberOfPages() > 200)
            .map(Book::toString)
            .collect(Collectors.toList());

        Supplier<Stream<String>> bookSupplier = () -> books.stream();

        long booksCount = bookSupplier.get().count();
        System.out.println("Is there any books with more than 200 pages: " + (booksCount > 0));

        System.out.println(joinStream(bookSupplier.get()));
        System.out.println();
    }

    private void bookWithHighestPageNumber(Stream<Book> bookStream) {
        Collector<Book, ?, Optional<Book>> bookCollector =
            Collectors.maxBy(Comparator.comparingInt(Book::getNumberOfPages));

        Optional<Book> book = bookStream.parallel().collect(bookCollector);

        System.out.println("Book with the highest page number:");
        System.out.println(book.get());
        System.out.println();
    }

    private void bookWithLowestPageNumber(Stream<Book> bookStream) {
        Collector<Book, ?, Optional<Book>> bookCollector =
            Collectors.minBy(Comparator.comparingInt(Book::getNumberOfPages));

        Optional<Book> book = bookStream.parallel().collect(bookCollector);

        System.out.println("Book with the lowest page number:");
        System.out.println(book.get());
        System.out.println();
    }

    private void booksWithSingleAuthor(Stream<Book> bookStream) {
        Comparator<Book> bookComparator = Comparator.comparing(Book::getNumberOfPages).thenComparing(Comparator.comparing(Book::getTitle));

        List<String> bookTitles = bookStream
            .filter(book -> (book.getAuthors().size() == 1))
            .sorted(bookComparator)
            .map(Book::getTitle)
            .collect(Collectors.toList());

        System.out.println("Books with single author, and sorted by number of pages and title:");

        System.out.println(joinStream(bookTitles.stream()));
        System.out.println();
    }

    private void printBooksWithForEach(Stream<Book> bookStream) {
        System.out.println("Books printed with forEach:");

        bookStream.forEach(System.out::println);
        System.out.println();
    }

    private void listAuthors(Stream<Book> bookStream) {
        List<String> authorNames = bookStream
            .flatMap(book -> book.getAuthors().stream())
            .map(Author::getName)
            .peek(authorName -> System.out.println("[DEBUG] Found author: " + authorName))
            .distinct()
            .peek(authorName -> System.out.println("[DEBUG] Author added: " + authorName))
            .collect(Collectors.toList());

        System.out.println("Authors:");
        System.out.println(joinStream(authorNames.stream()));
        System.out.println();
    }

}
