package com.springcore.scope;

import com.springcore.scope.prototype.PrototypeBean;
import com.springcore.scope.singletone.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

public class BeanInjectionStarter {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig2.class);
        SingletonBean firstSingleton = context.getBean(SingletonBean.class);
        PrototypeBean firstPrototype = firstSingleton.getPrototypeBean();
        SingletonBean secondSingleton = context.getBean(SingletonBean.class);
        PrototypeBean secondPrototype = secondSingleton.getPrototypeBean();
        Assert.isTrue(firstPrototype.equals(secondPrototype), "The same instance is returned");

    }
}
