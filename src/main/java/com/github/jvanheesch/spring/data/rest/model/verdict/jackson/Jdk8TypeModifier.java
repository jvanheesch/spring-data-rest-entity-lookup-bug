package com.github.jvanheesch.spring.data.rest.model.verdict.jackson;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.*;
import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;

import java.lang.reflect.Type;

/**
 * We need to ensure `Optional` is a `ReferenceType`
 */
public class Jdk8TypeModifier extends TypeModifier {
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
