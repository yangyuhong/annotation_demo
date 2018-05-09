package com.example.yuhongyang.testannotation.Annotion;

import android.app.Activity;
import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yuhong.yang on 2018/1/23.
 */

public class ViewInjectUtils {

    private static final String METHOD_SET_CONTENTVIEW = "setContentView";
    private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";
    private static final String METHOD_SET_ON_CLICK = "setOnClickListener";

    private static void injectContentView(Activity activty){
        Class<? extends Activity> clazz = activty.getClass();
        ContentView contentView = clazz.getAnnotation(ContentView.class);

        if (contentView != null){
            int contentViewLayoutId = contentView.value();
            try{
                Method method = clazz.getMethod(METHOD_SET_CONTENTVIEW,int.class);
                method.setAccessible(true);
                method.invoke(activty,contentViewLayoutId);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static void injectView(Activity activity){
        Class<? extends Activity> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            ViewInject viewInjectAnnotation = field.getAnnotation(ViewInject.class);
            if (viewInjectAnnotation != null){
                int viewId = viewInjectAnnotation.value();
                if (viewId != -1){
                    Log.e("Tag",viewId+"");
                    try {
                        Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID,int.class);
                        Object resView = method.invoke(activity,viewId);
                        field.setAccessible(true);
                        field.set(activity,resView);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }

    private static void injectEvents(Activity activity){
        Class<? extends Activity> clazz = activity.getClass();
        Method[] methods = clazz.getMethods();
        //遍历所有方法
        for (Method method : methods){
            Annotation[] annotations = method.getDeclaredAnnotations();
            //拿到方法上所有注解
            for (Annotation annotation: annotations){
                Class<? extends Annotation> annotationType = annotation.annotationType();
                //拿到注解上的注解
                EventBase eventBaseAnnotation = annotationType.getAnnotation(EventBase.class);
                //如果是eventbase
                if (eventBaseAnnotation != null){
                    //取出设置的监听器的名称 监听器类型，调用的方法名
                    String listenerSetter = eventBaseAnnotation.listenerSet();
                    String methodName = eventBaseAnnotation.methodName();
                    Class<?> listenerType = eventBaseAnnotation.listenerType();

                    try {
                        Method aMethod = annotationType.getDeclaredMethod("value");
                        //取出所有的viewid
                        int[] viewIds = (int[]) aMethod.invoke(annotation,null);

                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void inject(Activity activity){
        injectContentView(activity);
        injectView(activity);
        injectEvents(activity);
    }
}
