package com.meli.veterinaria.util;

import com.meli.veterinaria.exception.MissingDataException;

public class ValidationUtils {

    public static void validateRequiredField(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new MissingDataException(String.format("El campo '%s' es requerido", fieldName));
        }
    }

    public static <T extends Enum<T>> void validateEnumValue(T value, Class<T> enumClass, String fieldName) {
        if (value == null) {
            throw new MissingDataException(String.format("El campo '%s' es requerido", fieldName));
        }
        try {
            Enum.valueOf(enumClass, value.name());
        } catch (IllegalArgumentException e) {
            throw new MissingDataException(String.format("El valor del campo '%s' no está definido", fieldName));
        }
    }
}