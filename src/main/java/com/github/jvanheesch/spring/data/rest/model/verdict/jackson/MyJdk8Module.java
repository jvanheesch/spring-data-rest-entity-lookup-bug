package com.github.jvanheesch.spring.data.rest.model.verdict.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class MyJdk8Module extends SimpleModule {
    @Override
    public void setupModule(SetupContext context) {
        context.addSerializers(new Jdk8Serializers());
        context.addDeserializers(new Jdk8Deserializers());
        context.addTypeModifier(new Jdk8TypeModifier());
    }
}
