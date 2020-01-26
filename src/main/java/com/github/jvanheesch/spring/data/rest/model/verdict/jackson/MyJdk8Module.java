package com.github.jvanheesch.spring.data.rest.model.verdict.jackson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.ser.std.ReferenceTypeSerializer;
import com.fasterxml.jackson.databind.type.*;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;

import java.lang.reflect.Type;

public class MyJdk8Module extends SimpleModule {
    @Override
    public void setupModule(SetupContext context) {
        context.addSerializers(new Jdk8Serializers());
        context.addDeserializers(new Jdk8Deserializers());
        context.addTypeModifier(new Jdk8TypeModifier());
    }

    static class Jdk8Serializers extends Serializers.Base {
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

    static class Jdk8Deserializers extends Deserializers.Base {
        @Override
        public JsonDeserializer<?> findReferenceDeserializer(
                ReferenceType refType,
                DeserializationConfig config,
                BeanDescription beanDesc,
                TypeDeserializer contentTypeDeserializer,
                JsonDeserializer<?> contentDeserializer
        ) {
            return refType.hasRawClass(Optional.class)
                    ? new OptionalDeserializer(refType, null, contentTypeDeserializer, contentDeserializer)
                    : null;

        }
    }

    static class Jdk8TypeModifier extends TypeModifier {
        @Override
        public JavaType modifyType(JavaType type, Type jdkType, TypeBindings bindings, TypeFactory typeFactory) {
            if (type.getRawClass() == Optional.class) {
                return ReferenceType.upgradeFrom(new OptionalType(), new VerdictType());
            } else {
                return type;
            }
        }

        private static class VerdictType extends SimpleType {
            protected VerdictType() {
                super(Verdict.class);
            }
        }

        private static class OptionalType extends SimpleType {
            protected OptionalType() {
                super(Optional.class);
            }
        }
    }

    static class OptionalSerializer extends ReferenceTypeSerializer<Optional> {
        private static final long serialVersionUID = 1L;

        protected OptionalSerializer(
                ReferenceType fullType,
                boolean staticTyping,
                TypeSerializer vts,
                JsonSerializer<Object> ser
        ) {
            super(fullType, staticTyping, vts, ser);
        }

        protected OptionalSerializer(
                OptionalSerializer base,
                BeanProperty property,
                TypeSerializer vts,
                JsonSerializer<?> valueSer,
                NameTransformer unwrapper,
                Object suppressableValue,
                boolean suppressNulls
        ) {
            super(base, property, vts, valueSer, unwrapper, suppressableValue, suppressNulls);
        }

        @Override
        protected ReferenceTypeSerializer<Optional> withResolved(
                BeanProperty prop,
                TypeSerializer vts,
                JsonSerializer<?> valueSer,
                NameTransformer unwrapper
        ) {
            return new OptionalSerializer(this, prop, vts, valueSer, unwrapper, _suppressableValue, _suppressNulls);
        }

        @Override
        public ReferenceTypeSerializer<Optional> withContentInclusion(Object suppressableValue, boolean suppressNulls) {
            return new OptionalSerializer(this, _property, _valueTypeSerializer, _valueSerializer, _unwrapper, suppressableValue, suppressNulls);
        }

        @Override
        protected boolean _isValuePresent(Optional value) {
            return value.getVerdict() != null;
        }

        @Override
        protected Object _getReferenced(Optional value) {
            return value.getVerdict();
        }

        @Override
        protected Object _getReferencedIfPresent(Optional value) {
            return value.getVerdict();
        }
    }

    static class OptionalDeserializer extends ReferenceTypeDeserializer<Optional> {
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
            return new Optional();
        }

        @Override
        public Optional referenceValue(Object contents) {
            return new Optional((Verdict) contents);
        }

        @Override
        public Object getReferenced(Optional reference) {
            return reference.getVerdict();
        }

        @Override
        public Optional updateReference(Optional reference, Object contents) {
            return new Optional((Verdict) contents);
        }
    }
}
