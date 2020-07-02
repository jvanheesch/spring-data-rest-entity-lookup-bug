package com.github.jvanheesch.spring.data.rest.blah;

import com.github.jvanheesch.spring.data.rest.pck.ServiceA;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//@ComponentScan(basePackageClasses = ServiceA.class, excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, value = "com\\.example\\.ignore\\..*"))
@ComponentScan(
        basePackageClasses = ServiceA.class,
//        excludeFilters = @ComponentScan.Filter(
//                type = FilterType.REGEX,
//                pattern = "com\\.github\\.jvanheesch\\.spring\\.data\\.rest\\.blgah\\..*"
//        )
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                value = ServiceA.class
        )
)
public class Ctx {
}
