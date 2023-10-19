package com.example.Library.Enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

public enum BookStatus {
    OnHands(0),
    InLibrary(1);

    private final Integer value;

    BookStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    static public BookStatus getByValue(Integer number) {
        for (BookStatus status : values()) {
            if (Objects.equals(status.getValue(), number)) return status;
        }
        return null;
    }

    @Converter
    public static final class MemberStatusEnumConverter implements
            AttributeConverter<BookStatus, Integer> {
        @Override
        public Integer convertToDatabaseColumn(BookStatus attribute) {
            return attribute.getValue();
        }

        @Override
        public BookStatus convertToEntityAttribute(Integer dbData) {
            return BookStatus.getByValue(dbData);
        }
    }
}
