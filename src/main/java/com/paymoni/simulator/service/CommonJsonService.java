package com.paymoni.simulator.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommonJsonService {

    private final ObjectMapper objectMapper;

    public String toJson(Object value) throws Exception {
        return objectMapper.writeValueAsString(value);
    }

    public <T> T fromJson(String json, Class<T> type) throws Exception {
        return objectMapper.readValue(json, type);
    }

    public <T> T fromJson(String json, TypeReference<T> typeRef) throws Exception {
        return objectMapper.readValue(json, typeRef);
    }
}