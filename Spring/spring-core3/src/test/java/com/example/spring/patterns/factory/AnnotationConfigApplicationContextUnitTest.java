package com.example.spring.patterns.factory;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AnnotationConfigApplicationContextUnitTest {
    // Test case to check if a simple bean can be retrieved from the application context
    @Test @SuppressWarnings("resource") public void whenGetSimpleBean_thenReturnConstructedBean() {
        // Creating an application context with the provided configuration class
        ApplicationContext context = new AnnotationConfigApplicationContext(
                ApplicationConfig.class);
        // Getting the bean of type Foo from the application context
        Foo foo = context.getBean(Foo.class);
        // Asserting that the retrieved bean is not null
        assertNotNull(foo);
    }

    // Test case to check if a prototype bean can be retrieved from the application context
    @Test @SuppressWarnings("resource")
    public void whenGetPrototypeBean_thenReturnConstructedBean() {
        // Expected name for the prototype bean
        String expectedName = "Some name";
        // Creating an application context with the provided configuration class
        ApplicationContext context = new AnnotationConfigApplicationContext(
                ApplicationConfig.class);
        // Getting the bean of type Bar from the application context with the expected name as
        // argument
        Bar bar = context.getBean(Bar.class, expectedName);
        // Asserting that the retrieved bean is not null
        assertNotNull(bar);
        // Asserting that the name of the retrieved bean matches the expected name
        assertThat(bar.getName(), is(expectedName));
    }
}

