package com.oynee.portfolio.partner.adapter.persistence.support.converter;

import com.oynee.portfolio.partner.common.type.EnumCodeAware;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public abstract class EnumConverter<E extends EnumCodeAware> implements AttributeConverter<E, String> {

    private final Class<E> enumType;
    private final Map<String, E> enumMap;

    public EnumConverter(Class<E> enumType) {
        this.enumType = enumType;
        this.enumMap = Arrays.stream(enumType.getEnumConstants())
                .collect(Collectors.toUnmodifiableMap(EnumCodeAware::getCode, Function.identity()));

        log.debug("Created EnumMap: {}{}", enumType.getCanonicalName(), enumMap);
    }

    @Override
    public String convertToDatabaseColumn(E attribute) {
        return attribute == null ? null : attribute.getCode();
    }

    @Override
    public E convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        var enumObj = enumMap.get(dbData);
        if (enumObj == null) {
            var errorMessage = String.format("\"%s\"는 %s에 정의되지 않은 코드입니다.",
                    dbData, enumType.getCanonicalName());
            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return enumObj;
    }
}
