package com.epam.hcomtest.resource;

import static org.mockito.Mockito.*;

public class ResourceTestBuilder {

    private ResourceColor resourceColor = ResourceColor.DEFAULT;
    private int key = 0;
    private String value = "Default value";

    public Resource build() {
        Resource mockResource = mock(Resource.class);

        when(mockResource.getColor()).thenReturn(resourceColor);
        when(mockResource.getKey()).thenReturn(key);
        when(mockResource.getValue()).thenReturn(value);

        return mockResource;
    }

    public ResourceTestBuilder withColor(ResourceColor resourceColor) {
        this.resourceColor = resourceColor;

        return this;
    }

    public ResourceTestBuilder withKey(int key) {
        this.key = key;

        return this;
    }

    public ResourceTestBuilder withValue(String value) {
        this.value = value;

        return this;
    }

}
