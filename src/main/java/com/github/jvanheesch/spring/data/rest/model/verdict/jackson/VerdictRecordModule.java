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

import java.lang.reflect.Type;

/**
 * TODO_JORIS: JAVADOC!
 */
public class VerdictRecordModule extends SimpleModule {
    @Override
    public void setupModule(SetupContext context) {
        context.addSerializers(new VerdictRecordSerializers());
        // context.addDeserializers(new VerdictRecordDeserializers());
        context.addTypeModifier(new VerdictRecordTypeModifier());
    }

    static class VerdictRecordSerializers extends Serializers.Base {
        @Override
        public JsonSerializer<?> findReferenceSerializer(
                SerializationConfig config,
                ReferenceType refType,
                BeanDescription beanDesc,
                TypeSerializer contentTypeSerializer,
                JsonSerializer<Object> contentValueSerializer
        ) {
            JsonSerializer<?> jsonSerializer;
            if (VerdictRecord.class.isAssignableFrom(refType.getRawClass())) {
                jsonSerializer = new VerdictRecordSerializer(
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

//    static class VerdictRecordDeserializers extends Deserializers.Base {
//        @Override
//        public JsonDeserializer<?> findReferenceDeserializer(
//                ReferenceType refType,
//                DeserializationConfig config,
//                BeanDescription beanDesc,
//                TypeDeserializer contentTypeDeserializer,
//                JsonDeserializer<?> contentDeserializer
//        ) {
//            return refType.hasRawClass(VerdictRecord.class)
//                    ? new VerdictRecordDeserializer(refType, null, contentTypeDeserializer, contentDeserializer)
//                    : null;
//
//        }
//    }

    static class VerdictRecordTypeModifier extends TypeModifier {
        @Override
        public JavaType modifyType(JavaType type, Type jdkType, TypeBindings bindings, TypeFactory typeFactory) {
            if (type.getRawClass() == VerdictRecord.class) {
                return ReferenceType.upgradeFrom(new VerdictRecordType(), new VerdictType());
            } else {
                return type;
            }
        }

        private static class VerdictType extends SimpleType {
            protected VerdictType() {
                super(String.class);
            }
        }

        private static class VerdictRecordType extends SimpleType {
            protected VerdictRecordType() {
                super(VerdictRecord.class);
            }
        }
    }

    static class VerdictRecordSerializer extends ReferenceTypeSerializer<VerdictRecord> {
        private static final long serialVersionUID = 1L;

        protected VerdictRecordSerializer(
                ReferenceType fullType,
                boolean staticTyping,
                TypeSerializer vts,
                JsonSerializer<Object> ser
        ) {
            super(fullType, staticTyping, vts, ser);
        }

        protected VerdictRecordSerializer(
                VerdictRecordSerializer base,
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
        protected ReferenceTypeSerializer<VerdictRecord> withResolved(
                BeanProperty prop,
                TypeSerializer vts,
                JsonSerializer<?> valueSer,
                NameTransformer unwrapper
        ) {
            return new VerdictRecordSerializer(this, prop, vts, valueSer, unwrapper, _suppressableValue, _suppressNulls);
        }

        @Override
        public ReferenceTypeSerializer<VerdictRecord> withContentInclusion(Object suppressableValue, boolean suppressNulls) {
            return new VerdictRecordSerializer(this, _property, _valueTypeSerializer, _valueSerializer, _unwrapper, suppressableValue, suppressNulls);
        }

        @Override
        protected boolean _isValuePresent(VerdictRecord verdictRecord) {
            return verdictRecord.getValue() != null;
        }

        @Override
        protected Object _getReferenced(VerdictRecord verdictRecord) {
            // TODO_JORIS: why does this not work??? see optional.
            return verdictRecord.getValue();
        }

        // hier moeten we 3x inkomen, once for each verdictrecord !! gebeurt in test, maar slechts 2x in SDR !!!!!
        @Override
        protected Object _getReferencedIfPresent(VerdictRecord verdictRecord) {
            return verdictRecord.getValue();
        }
    }

//    static class VerdictRecordDeserializer extends ReferenceTypeDeserializer<VerdictRecord> {
//        private static final long serialVersionUID = 1L;
//
//        public VerdictRecordDeserializer(
//                JavaType fullType,
//                ValueInstantiator inst,
//                TypeDeserializer typeDeser,
//                JsonDeserializer<?> deser
//        ) {
//            super(fullType, inst, typeDeser, deser);
//        }
//
//        @Override
//        public VerdictRecordDeserializer withResolved(TypeDeserializer typeDeser, JsonDeserializer<?> valueDeser) {
//            return new VerdictRecordDeserializer(_fullType, _valueInstantiator, typeDeser, valueDeser);
//        }
//
//        // TODO_JORIS snappen
//        @Override
//        public VerdictRecord getNullValue(DeserializationContext ctxt) {
//            return new VerdictRecord();
//        }
//
//        @Override
//        public VerdictRecord referenceValue(Object contents) {
//            return new VerdictRecord((String) contents);
//        }
//
//        @Override
//        public Object getReferenced(VerdictRecord reference) {
//            return reference.getValue();
//        }
//
//        @Override
//        public VerdictRecord updateReference(VerdictRecord reference, Object contents) {
//            return new VerdictRecord((String) contents);
//        }
//    }
}
