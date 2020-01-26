package com.github.jvanheesch.spring.data.rest.model.verdict.jackson;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;

final class OptionalDeserializer extends ReferenceTypeDeserializer<Optional> {
    private static final long serialVersionUID = 1L;

    public OptionalDeserializer(
            JavaType fullType,
            ValueInstantiator inst,
            TypeDeserializer typeDeser,
            JsonDeserializer<?> deser
    ) {
        super(fullType, inst, typeDeser, deser);
    }

    @Override
    public OptionalDeserializer withResolved(TypeDeserializer typeDeser, JsonDeserializer<?> valueDeser) {
        return new OptionalDeserializer(_fullType, _valueInstantiator, typeDeser, valueDeser);
    }

    // TODO_JORIS snappen
    @Override
    public Optional getNullValue(DeserializationContext ctxt) {
        return Optional.empty();
    }

    @Override
    public Optional referenceValue(Object contents) {
        return Optional.ofNullable((Verdict) contents);
    }

    @Override
    public Object getReferenced(Optional reference) {
        return reference.getVerdict();
    }

    @Override
    public Optional updateReference(Optional reference, Object contents) {
        return Optional.ofNullable((Verdict) contents);
    }
}
