package com.epam.hcomtest.getdata.controller;

import com.epam.hcomtest.datastore.model.DataStoreReader;
import com.epam.hcomtest.resource.ResourceColor;
import com.epam.hcomtest.resource.ResourceUIModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class GetDataController {

    private static final String DATA_PAGE_VIEW = "dataPage";
    private static final String HOME_PAGE_VIEW = "index";

    @Autowired
    private DataStoreReader dataStoreReader;
    @Autowired
    private Map<ResourceColor, String> resourceColorDataStoreMap;

    @PostMapping
    public ModelAndView getData() {
        ResourceUIModel resourceUIModel = new ResourceUIModel(dataStoreReader, resourceColorDataStoreMap);

        return new ModelAndView(DATA_PAGE_VIEW, "resources", resourceUIModel.getResources());
    }

    @GetMapping
    public ModelAndView getHomePage() {
        return new ModelAndView(HOME_PAGE_VIEW);
    }

}
