package com.epam.mentoring.mapper;

import com.epam.mentoring.api.request.BookRequest;
import com.epam.mentoring.api.response.BookResponse;
import com.epam.mentoring.domain.BookDAO;
import com.google.common.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public final class BookMapper extends AbstractModelMapper {

    public BookDAO mapToDao(BookRequest request) {
        Type type = BookDAO.class;

        return map(request, type);
    }

    public BookDAO mapToDao(BookResponse response) {
        Type type = BookDAO.class;

        return map(response, type);
    }

    public List<BookDAO> mapToDao(List<?> source) {
        Type type = new TypeToken<List<BookDAO>>() {}.getType();

        return map(source, type);
    }

    public BookRequest mapToRequest(BookDAO dao) {
        Type type = BookRequest.class;

        return map(dao, type);
    }

    public BookRequest mapToRequest(BookResponse response) {
        Type type = BookRequest.class;

        return map(response, type);
    }

    public List<BookRequest> mapToRequest(List<?> source) {
        Type type = new TypeToken<List<BookRequest>>() {}.getType();

        return map(source, type);
    }

    public BookResponse mapToResponse(BookDAO dao) {
        Type type = BookResponse.class;

        return map(dao, type);
    }

    public BookResponse mapToResponse(BookRequest request) {
        Type type = BookResponse.class;

        return map(request, type);
    }

    public List<BookResponse> mapToResponse(List<?> source) {
        Type type = new TypeToken<List<BookResponse>>() {}.getType();

        return map(source, type);
    }

}
