package br.com.ecommerce.infrastructure.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import static java.util.Objects.isNull;

@Converter
@RequiredArgsConstructor
public abstract class JsonAttributeConverter<T> extends TypeReference<T> implements AttributeConverter<T, String> {
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public String convertToDatabaseColumn(T attribute) {
        if (isNull(attribute)) {
            return null;
        }
        return objectMapper.writeValueAsString(attribute);
    }

    @Override
    @SneakyThrows
    public T convertToEntityAttribute(String dbData) {
        if (isNull(dbData) || dbData.isBlank()) {
            return null;
        }
        //H2 CHECK
        if (dbData.startsWith("\"") && dbData.endsWith("\"")) {
            dbData = dbData.substring(1, dbData.length() - 1)
                    .replaceAll("\\\\", "");
        }
        return objectMapper.readValue(dbData, this);
    }
}