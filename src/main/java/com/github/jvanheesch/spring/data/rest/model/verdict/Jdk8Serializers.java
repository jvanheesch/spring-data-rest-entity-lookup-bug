package com.github.jvanheesch.spring.data.rest.model.verdict;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.type.ReferenceType;

public class Jdk8Serializers extends Serializers.Base {
    @Override
    public JsonSerializer<?> findReferenceSerializer(
            SerializationConfig config,
            ReferenceType refType,
            BeanDescription beanDesc,
            TypeSerializer contentTypeSerializer,
            JsonSerializer<Object> contentValueSerializer
    ) {
        return Optional.class.isAssignableFrom(refType.getRawClass())
                ? new OptionalSerializer(
                refType,
                (contentTypeSerializer == null) && config.isEnabled(MapperFeature.USE_STATIC_TYPING),
                contentTypeSerializer,
                contentValueSerializer)
                : null;
    }
}
