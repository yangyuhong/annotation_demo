package com.example.yuhongyang.testannotation;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.yuhongyang.testannotation.Annotion.ContentView;
import com.example.yuhongyang.testannotation.Annotion.ViewInject;
import com.example.yuhongyang.testannotation.Annotion.ViewInjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@ContentView(value = R.layout.activity_main)
public class MainActivity extends AppCompatActivity {


    @ViewInject(R.id.text)
    private TextView tv;
    @TestAnnotation("Hello,Annotation")
    private String testAnnotation;

    private int flavour;

    public static final int VANILLA = 0;
    public static final int CHOCOLATE = 1;
    public static final int STRAWBERRY = 2;

    @IntDef(flag = true,value={VANILLA, CHOCOLATE, STRAWBERRY})
    public @interface Flavour {
    }

    @Flavour
    public int getFlavour() {
        return flavour;
    }

    public void setFlavour(@Flavour int flavour) {
        this.flavour = flavour;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        ViewInjectUtils.inject(this);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("test successful");
            }
        });


//        String name = "aaaaa";
//        setHello(R.string.app_name);
//        setFlavour(MainActivity.VANILLA & MainActivity.CHOCOLATE);




    }
    public void setHello(@StringRes int id){


    }



    public static void main(){
        try {
            Class cls = Class.forName("TestAnnotation.MainActivity");
            Field[] declaredField = cls.getDeclaredFields();
            for (Field field : declaredField){
                TestAnnotation annotation = field.getAnnotation(TestAnnotation.class);
                if (annotation !=null){
                    String value = annotation.value();
                    try{
//                        Method method =
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
