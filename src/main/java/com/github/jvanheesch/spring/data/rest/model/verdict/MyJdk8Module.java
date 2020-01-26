package com.github.jvanheesch.spring.data.rest.model.verdict;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jdk8.*;

public class MyJdk8Module extends Module
{
    protected boolean _cfgHandleAbsentAsNull = false;

    @Override
    public void setupModule(SetupContext context) {
        context.addSerializers(new Jdk8Serializers());
        context.addDeserializers(new Jdk8Deserializers());
        // And to fully support Optionals, need to modify type info:
        context.addTypeModifier(new Jdk8TypeModifier());

        // Allow enabling "treat Optional.empty() like Java nulls"
        if (_cfgHandleAbsentAsNull) {
            context.addBeanSerializerModifier(new Jdk8BeanSerializerModifier());
        }
    }

    @Override
    public Version version() {
        return PackageVersion.VERSION;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    // TODO_JORIS rename even tho not necessary
    @Override
    public String getModuleName() {
        return "Jdk8Module";
    }
}
