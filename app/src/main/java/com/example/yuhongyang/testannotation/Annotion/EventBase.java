package com.example.yuhongyang.testannotation.Annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yuhong.yang on 2018/1/23.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface EventBase {
    Class<?> listenerType();
    String listenerSet();
    String methodName();
}
