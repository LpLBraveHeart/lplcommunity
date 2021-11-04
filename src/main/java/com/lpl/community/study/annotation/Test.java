package com.lpl.community.study.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) {
        try {
            Class clazz=Class.forName("com.lpl.community.study.annotation.Student");
            //获得类的所有注解
            Annotation[] annotations=clazz.getAnnotations();
            for (Annotation annotation:annotations){
                System.out.println(annotation);
            }
            //获取类的指定的注解
            LplStudent lplStudent=(LplStudent) clazz.getAnnotation(LplStudent.class);
            System.out.println(lplStudent);
            //获取类的属性的注解
            Field field=clazz.getDeclaredField("userName");
            LplField lplField=field.getAnnotation(LplField.class);
            System.out.println(lplField.columnName() + "--" + lplField.type() + "--" + lplField.length());


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
