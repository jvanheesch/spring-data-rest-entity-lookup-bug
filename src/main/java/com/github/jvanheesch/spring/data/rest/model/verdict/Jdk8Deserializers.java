package com.github.jvanheesch.spring.data.rest.model.verdict;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ReferenceType;

public class Jdk8Deserializers extends Deserializers.Base
{
    @Override // since 2.7
    public JsonDeserializer<?> findReferenceDeserializer(ReferenceType refType,
            DeserializationConfig config, BeanDescription beanDesc,
            TypeDeserializer contentTypeDeserializer, JsonDeserializer<?> contentDeserializer)
    {
        if (refType.hasRawClass(Optional.class)) {
            return new OptionalDeserializer(refType, null, contentTypeDeserializer,contentDeserializer);
        }
        return null;
    }
}
