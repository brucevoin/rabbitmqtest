package com.test.rabbitmq.rabbitmqtest.annotationtest;

import java.lang.reflect.Method;

public class TestCLA
{
    /**
     * 被注释的三个方法
     */
    @Test(id = 1, description = "hello method1")
    public void method1() {
    }
    @Test(id = 2)
    public void method2() {
    }
    @Test(id = 3, description = "last method3")
    public static void main(String[] args) {
        Method[] methods = TestCLA.class.getDeclaredMethods();
        for (Method method : methods) {
            //判断方法中是否有指定注解类型的注解
            boolean hasAnnotation = method.isAnnotationPresent(Test.class);
            if (hasAnnotation) {
                //根据注解类型返回方法的指定类型注解
                Test annotation = method.getAnnotation(Test.class);
                System.out.println("Test(method=" + method.getName() + ",id=" + annotation.id()
                        + ",description=" + annotation.description() + ")");
            }
        }
    }
}
