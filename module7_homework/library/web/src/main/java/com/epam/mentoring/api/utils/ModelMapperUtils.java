package com.epam.mentoring.api.utils;

import com.epam.mentoring.api.request.AuthorRequest;
import com.epam.mentoring.api.request.BookRequest;
import com.epam.mentoring.api.request.UserRequest;
import com.epam.mentoring.api.response.AuthorResponse;
import com.epam.mentoring.api.response.BookResponse;
import com.epam.mentoring.api.response.InventoryResponse;
import com.epam.mentoring.api.response.UserResponse;
import com.epam.mentoring.domain.AuthorDAO;
import com.epam.mentoring.domain.BookDAO;
import com.epam.mentoring.domain.UserDAO;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class ModelMapperUtils {

    public static final Type AUTHOR_LIST_TYPE = new TypeToken<List<AuthorDAO>>() {}.getType();
    public static final Type AUTHOR_RESPONSE_TYPE = AuthorResponse.class;
    public static final Type AUTHOR_RESPONSE_LIST_TYPE = new TypeToken<List<AuthorResponse>>() {}.getType();
    public static final Type AUTHOR_REQUEST_LIST_TYPE = new TypeToken<List<AuthorRequest>>() {}.getType();

    public static final Type BOOK_TYPE = BookDAO.class;
    public static final Type BOOK_RESPONSE_TYPE = BookResponse.class;
    public static final Type BOOK_RESPONSE_LIST_TYPE = new TypeToken<List<BookResponse>>() {}.getType();
    public static final Type BOOK_REQUEST_TYPE = BookRequest.class;

    public static final Type INVENTORY_RESPONSE_LIST_TYPE = new TypeToken<List<InventoryResponse>>() {}.getType();

    public static final Type USER_TYPE = UserDAO.class;
    public static final Type USER_LIST_TYPE = new TypeToken<List<UserDAO>>() {}.getType();
    public static final Type USER_RESPONSE_TYPE = UserResponse.class;
    public static final Type USER_RESPONSE_LIST_TYPE = new TypeToken<List<UserResponse>>() {}.getType();
    public static final Type USER_REQUEST_TYPE = UserRequest.class;

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelMapperUtils.class);

    @Autowired
    private Gson gson;
    @Autowired
    private ModelMapper modelMapper;

    public <S, D> D map(S objectToMap, Type mapToType) {
        if (objectToMap == null) {
            return null;
        }

        D response = modelMapper.map(objectToMap, mapToType);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("{}: {}", mapToType.getTypeName(), gson.toJson(response));
        }

        return response;
    }

}
