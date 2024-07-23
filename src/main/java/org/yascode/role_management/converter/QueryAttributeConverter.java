package org.yascode.role_management.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;
import org.yascode.role_management.querybuilder.Query;

@Converter
@Slf4j
public class QueryAttributeConverter implements AttributeConverter<Query, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(Query query) {
        try {
            return objectMapper.writeValueAsString(query);
        } catch (JsonProcessingException jpe) {
            log.warn("Cannot convert Query into JSON");
            return null;
        }
    }

    @Override
    public Query convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Query.class);
        } catch (JsonProcessingException e) {
            log.warn("Cannot convert JSON into Query");
            return null;
        }
    }
}
