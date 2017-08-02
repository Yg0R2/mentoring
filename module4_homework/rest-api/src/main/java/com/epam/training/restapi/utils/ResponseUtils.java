package com.epam.training.restapi.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {

    public static ResponseEntity<Object> getResponseEntity(HttpStatus httpStatus, String errorMessage) {
        String body = getResponseBody(httpStatus, errorMessage);

        return new ResponseEntity<>(body, new HttpHeaders(), httpStatus);
    }

    public static String getResponseBody(HttpStatus httpStatus, String errorMessage) {
        StringBuilder sb = new StringBuilder();
        sb.append("{'Response':");
        sb.append(getJsonFromHttpStatus(httpStatus));

        if (StringUtils.isNotEmpty(errorMessage)) {
            sb.append(",'ErrorMessage':'");
            sb.append(errorMessage);
            sb.append("'");
        }

        sb.append("}");

        return sb.toString();
    }

    private static String getJsonFromHttpStatus(HttpStatus status) {
        return "{\"" + status.toString() + "\":\"" + status.getReasonPhrase() + "\"}";
    }


}
