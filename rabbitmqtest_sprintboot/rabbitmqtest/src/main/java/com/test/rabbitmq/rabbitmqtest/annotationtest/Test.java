package com.test.rabbitmq.rabbitmqtest.annotationtest;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
//VM将在运行期也保留注释，因此可以通过反射机制读取注解的信息
@Retention(RetentionPolicy.RUNTIME)
//将此注解包含在javadoc中
@Documented
//允许子类继承父类中的注解
@Inherited
public @interface Test {
    public int id();
    public String description() default "no description";
}
