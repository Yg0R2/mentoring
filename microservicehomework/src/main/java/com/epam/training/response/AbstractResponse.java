package com.epam.training.response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class AbstractResponse {

    public String toFormattedString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String jsonString = gson.toJson(this);

        return jsonString.replace("\n", "<br>").replace(": ", ":&nbsp;").replace(" ", "&nbsp;&nbsp;&nbsp;&nbsp;");
    }

    @Override
    public String toString() {
        Gson gson = new Gson();

        return gson.toJson(this);
    }

}
