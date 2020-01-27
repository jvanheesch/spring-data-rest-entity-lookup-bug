package com.github.jvanheesch.spring.data.rest.model.jackson;

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
import com.github.jvanheesch.spring.data.rest.model.StringContainer;

import java.lang.reflect.Type;

/**
 * Based on {@link com.fasterxml.jackson.datatype.jdk8.Jdk8Module}.
 */
public class StringContainerModule extends SimpleModule {
    @Override
    public void setupModule(SetupContext context) {
        context.addSerializers(new StringContainerSerializers());
        context.addDeserializers(new StringContainerDeserializers());
        context.addTypeModifier(new StringContainerTypeModifier());
    }

    static class StringContainerSerializers extends Serializers.Base {
        @Override
        public JsonSerializer<?> findReferenceSerializer(
                SerializationConfig config,
                ReferenceType refType,
                BeanDescription beanDesc,
                TypeSerializer contentTypeSerializer,
                JsonSerializer<Object> contentValueSerializer
        ) {
            JsonSerializer<?> jsonSerializer;
            if (StringContainer.class.isAssignableFrom(refType.getRawClass())) {
                jsonSerializer = new StringContainerSerializer(
                        refType,
                        (contentTypeSerializer == null) && config.isEnabled(MapperFeature.USE_STATIC_TYPING),
                        contentTypeSerializer,
                        contentValueSerializer);
            } else {
                jsonSerializer = null;
            }
            return jsonSerializer;
        }
    }

    static class StringContainerSerializer extends ReferenceTypeSerializer<StringContainer> {
        private static final long serialVersionUID = 1L;

        protected StringContainerSerializer(
                ReferenceType fullType,
                boolean staticTyping,
                TypeSerializer vts,
                JsonSerializer<Object> ser
        ) {
            super(fullType, staticTyping, vts, ser);
        }

        protected StringContainerSerializer(
                StringContainerSerializer base,
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
        protected ReferenceTypeSerializer<StringContainer> withResolved(
                BeanProperty prop,
                TypeSerializer vts,
                JsonSerializer<?> valueSer,
                NameTransformer unwrapper
        ) {
            return new StringContainerSerializer(this, prop, vts, valueSer, unwrapper, _suppressableValue, _suppressNulls);
        }

        @Override
        public ReferenceTypeSerializer<StringContainer> withContentInclusion(Object suppressableValue, boolean suppressNulls) {
            return new StringContainerSerializer(this, _property, _valueTypeSerializer, _valueSerializer, _unwrapper, suppressableValue, suppressNulls);
        }

        @Override
        protected boolean _isValuePresent(StringContainer stringContainer) {
            return stringContainer.getValue() != null;
        }

        // TODO_JORIS compare with jackson
        @Override
        protected Object _getReferenced(StringContainer stringContainer) {
            // TODO_JORIS: why does this not work??? see optional.
            return stringContainer.getValue();
        }

        @Override
        protected Object _getReferencedIfPresent(StringContainer stringContainer) {
            return stringContainer.getValue();
        }
    }


    static class StringContainerDeserializers extends Deserializers.Base {
        @Override
        public JsonDeserializer<?> findReferenceDeserializer(
                ReferenceType refType,
                DeserializationConfig config,
                BeanDescription beanDesc,
                TypeDeserializer contentTypeDeserializer,
                JsonDeserializer<?> contentDeserializer
        ) {
            return refType.hasRawClass(StringContainer.class)
                    ? new StringContainerDeserializer(refType, null, contentTypeDeserializer, contentDeserializer)
                    : null;
        }
    }

    static class StringContainerDeserializer extends ReferenceTypeDeserializer<StringContainer> {
        private static final long serialVersionUID = 1L;

        public StringContainerDeserializer(
                JavaType fullType,
                ValueInstantiator inst,
                TypeDeserializer typeDeser,
                JsonDeserializer<?> deser
        ) {
            super(fullType, inst, typeDeser, deser);
        }

        @Override
        public StringContainerDeserializer withResolved(TypeDeserializer typeDeser, JsonDeserializer<?> valueDeser) {
            return new StringContainerDeserializer(_fullType, _valueInstantiator, typeDeser, valueDeser);
        }

        // TODO_JORIS snappen
        @Override
        public StringContainer getNullValue(DeserializationContext ctxt) {
            return new StringContainer();
        }

        @Override
        public StringContainer referenceValue(Object contents) {
            return new StringContainer((String) contents);
        }

        @Override
        public Object getReferenced(StringContainer reference) {
            return reference.getValue();
        }

        @Override
        public StringContainer updateReference(StringContainer reference, Object contents) {
            return new StringContainer((String) contents);
        }
    }

    static class StringContainerTypeModifier extends TypeModifier {
        @Override
        public JavaType modifyType(JavaType type, Type jdkType, TypeBindings bindings, TypeFactory typeFactory) {
            if (type.getRawClass() == StringContainer.class) {
                return ReferenceType.upgradeFrom(new StringContainerType(), new StringType());
            } else {
                return type;
            }
        }

        private static class StringType extends SimpleType {
            protected StringType() {
                super(String.class);
            }
        }

        private static class StringContainerType extends SimpleType {
            protected StringContainerType() {
                super(StringContainer.class);
            }
        }
    }
}
