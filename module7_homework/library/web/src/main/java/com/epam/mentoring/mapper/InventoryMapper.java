package com.epam.mentoring.mapper;

import com.epam.mentoring.request.InventoryRequest;
import com.epam.mentoring.response.InventoryResponse;
import com.epam.mentoring.domain.InventoryDAO;
import com.google.common.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public final class InventoryMapper extends AbstractModelMapper {

    public InventoryDAO mapToDao(InventoryRequest request) {
        Type type = InventoryDAO.class;

        return map(request, type);
    }

    public InventoryDAO mapToDao(InventoryResponse response) {
        Type type = InventoryDAO.class;

        return map(response, type);
    }

    public List<InventoryDAO> mapToDao(List<?> source) {
        Type type = new TypeToken<List<InventoryDAO>>() {}.getType();

        return map(source, type);
    }

    public InventoryRequest mapToRequest(InventoryDAO dao) {
        Type type = InventoryRequest.class;

        return map(dao, type);
    }

    public InventoryRequest mapToRequest(InventoryResponse response) {
        Type type = InventoryRequest.class;

        return map(response, type);
    }

    public List<InventoryRequest> mapToRequest(List<?> source) {
        Type type = new TypeToken<List<InventoryRequest>>() {}.getType();

        return map(source, type);
    }

    public InventoryResponse mapToResponse(InventoryDAO dao) {
        Type type = InventoryResponse.class;

        return map(dao, type);
    }

    public InventoryResponse mapToResponse(InventoryRequest request) {
        Type type = InventoryResponse.class;

        return map(request, type);
    }

    public List<InventoryResponse> mapToResponse(List<?> source) {
        Type type = new TypeToken<List<InventoryResponse>>() {}.getType();

        return map(source, type);
    }

}
