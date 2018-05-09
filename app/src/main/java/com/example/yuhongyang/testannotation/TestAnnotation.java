package com.example.yuhongyang.testannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yuhong.yang on 2018/1/22.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TestAnnotation {
    String value();
    String[] value1() default "hello testannotaion";
}
