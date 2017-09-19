package com.arnoldgalovics.jpa.service;

import com.arnoldgalovics.jpa.repository.domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Service
public class BookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void insert() {
        Book book = new Book("Effective Java");

        entityManager.persist(book);

        LOGGER.info("After persist.");
    }

    @Transactional
    public void method() {
        List<Book> books = entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
        books.forEach(System.out::println);
    }

    @Transactional
    public void method2() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery(Book.class);
        query.from(Book.class);

        List<Book> books = entityManager.createQuery(query).getResultList();
        books.forEach(System.out::println);
    }

    @Transactional
    public void update() {
        Book book = entityManager.find(Book.class, 1);
        book.setName("Effective Java 2");

        LOGGER.info("After update.");
    }

}
