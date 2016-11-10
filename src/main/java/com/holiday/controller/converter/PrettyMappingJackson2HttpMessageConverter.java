package com.holiday.controller.converter;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Collections;

public class PrettyMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

    public PrettyMappingJackson2HttpMessageConverter() {
        super();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT.INDENT_OUTPUT);
        setSupportedMediaTypes(Collections.singletonList(new MediaType("application", "json+pretty", DEFAULT_CHARSET)));
    }

}
