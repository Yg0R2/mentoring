package com.epam.mentoring.mapper;

import com.epam.mentoring.api.request.AuthorRequest;
import com.epam.mentoring.api.response.AuthorResponse;
import com.epam.mentoring.domain.AuthorDAO;
import com.google.common.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public final class AuthorMapper extends AbstractModelMapper {

    public AuthorDAO mapToDao(AuthorRequest request) {
        Type type = AuthorDAO.class;

        return map(request, type);
    }

    public AuthorDAO mapToDao(AuthorResponse response) {
        Type type = AuthorDAO.class;

        return map(response, type);
    }

    public List<AuthorDAO> mapToDao(List<?> source) {
        Type type = new TypeToken<List<AuthorDAO>>() {}.getType();

        return map(source, type);
    }

    public AuthorRequest mapToRequest(AuthorDAO dao) {
        Type type = AuthorRequest.class;

        return map(dao, type);
    }

    public AuthorRequest mapToRequest(AuthorResponse response) {
        Type type = AuthorRequest.class;

        return map(response, type);
    }

    public List<AuthorRequest> mapToRequest(List<?> source) {
        Type type = new TypeToken<List<AuthorRequest>>() {}.getType();

        return map(source, type);
    }

    public AuthorResponse mapToResponse(AuthorDAO dao) {
        Type type = AuthorResponse.class;

        return map(dao, type);
    }

    public AuthorResponse mapToResponse(AuthorRequest request) {
        Type type = AuthorResponse.class;

        return map(request, type);
    }

    public List<AuthorResponse> mapToResponse(List<?> source) {
        Type type = new TypeToken<List<AuthorResponse>>() {}.getType();

        return map(source, type);
    }

}
