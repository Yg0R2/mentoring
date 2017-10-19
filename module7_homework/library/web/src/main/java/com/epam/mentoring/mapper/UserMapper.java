package com.epam.mentoring.mapper;

import com.epam.mentoring.api.request.UserRequest;
import com.epam.mentoring.api.response.UserResponse;
import com.epam.mentoring.domain.UserDAO;
import com.google.common.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public final class UserMapper extends AbstractModelMapper {

    public UserDAO mapToDao(UserRequest request) {
        Type type = UserDAO.class;

        return map(request, type);
    }

    public UserDAO mapToDao(UserResponse response) {
        Type type = UserDAO.class;

        return map(response, type);
    }

    public List<UserDAO> mapToDao(List<?> source) {
        Type type = new TypeToken<List<UserDAO>>() {}.getType();

        return map(source, type);
    }

    public UserRequest mapToRequest(UserDAO dao) {
        Type type = UserRequest.class;

        return map(dao, type);
    }

    public UserRequest mapToRequest(UserResponse response) {
        Type type = UserRequest.class;

        return map(response, type);
    }

    public List<UserRequest> mapToRequest(List<?> source) {
        Type type = new TypeToken<List<UserRequest>>() {}.getType();

        return map(source, type);
    }

    public UserResponse mapToResponse(UserDAO dao) {
        Type type = UserResponse.class;

        return map(dao, type);
    }

    public UserResponse mapToResponse(UserRequest request) {
        Type type = UserResponse.class;

        return map(request, type);
    }

    public List<UserResponse> mapToResponse(List<?> source) {
        Type type = new TypeToken<List<UserResponse>>() {}.getType();

        return map(source, type);
    }

}
