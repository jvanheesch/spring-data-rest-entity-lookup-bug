package com.github.jvanheesch.spring.data.rest.blah;

import com.github.jvanheesch.spring.data.rest.pck.ServiceA;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackageClasses = ServiceA.class,
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                value = ServiceA.class
        )
)
public class Ctx {
}
