package com.example.yuhongyang.testannotation.Annotion;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * Created by yuhong.yang on 2018/1/23.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@EventBase(listenerType = View.OnClickListener.class,listenerSet = "setOnClickListener",methodName = "onClick")
public @interface OnClick {
   int[] value();
}
