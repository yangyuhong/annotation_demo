package com.example.yuhongyang.testannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yuhong.yang on 2018/1/23.
 */


@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD,ElementType.TYPE})
public @interface UserApi {
    String value();
}
