package com.github.jvanheesch.spring.data.rest.blah;

import com.github.jvanheesch.spring.data.rest.pck.BookRepository2;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackageClasses = BookRepository2.class,
        includeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = "com\\.github\\.jvanheesch\\.spring\\.data\\.rest\\.pck\\.BookRepository2"
        ),
        useDefaultFilters = false
)
public class Ctx {
}
