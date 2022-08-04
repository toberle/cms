package com.example.cms.domain.converter;

import javax.persistence.AttributeConverter;

public class BooleanConverter implements AttributeConverter<Boolean, String> {

    private static final String TRUE_VALUE = "Y";
    private static final String FALSE_VALUE = "N";

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute ? TRUE_VALUE : FALSE_VALUE;
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return dbData == null ? null : TRUE_VALUE.equals(dbData);
    }
}
