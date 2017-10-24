package com.epam.mentoring.mapper;

import com.epam.mentoring.api.request.BorrowRequest;
import com.epam.mentoring.api.response.BorrowResponse;
import com.epam.mentoring.domain.BorrowDAO;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public final class BorrowMapper extends AbstractModelMapper {

    public BorrowDAO mapToDao(BorrowRequest request) {
        Type type = BorrowDAO.class;

        return map(request, type);
    }

    public BorrowDAO mapToDao(BorrowResponse response) {
        Type type = BorrowDAO.class;

        return map(response, type);
    }

    public List<BorrowDAO> mapToDao(List<?> source) {
        Type type = new TypeToken<List<BorrowDAO>>() {}.getType();

        return map(source, type);
    }

    public BorrowRequest mapToRequest(BorrowDAO dao) {
        Type type = BorrowRequest.class;

        return map(dao, type);
    }

    public BorrowRequest mapToRequest(BorrowResponse response) {
        Type type = BorrowRequest.class;

        return map(response, type);
    }

    public List<BorrowRequest> mapToRequest(List<?> source) {
        Type type = new TypeToken<List<BorrowRequest>>() {}.getType();

        return map(source, type);
    }

    public BorrowResponse mapToResponse(BorrowDAO dao) {
        Type type = BorrowResponse.class;

        return map(dao, type);
    }

    public BorrowResponse mapToResponse(BorrowRequest request) {
        Type type = BorrowResponse.class;

        return map(request, type);
    }

    public List<BorrowResponse> mapToResponse(List<?> source) {
        Type type = new TypeToken<List<BorrowResponse>>() {}.getType();

        return map(source, type);
    }

}
