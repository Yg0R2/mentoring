package com.epam.mentoring.exceptionhandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;

public class ErrorInfo implements Serializable {

    public final String message;
    public final Map<String, String[]> parameters;
    public final String url;

    public ErrorInfo(Exception e, HttpServletRequest request) {
        message = e.getLocalizedMessage();
        parameters = request.getParameterMap();
        url = request.getRequestURI();
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(this);
    }
}
