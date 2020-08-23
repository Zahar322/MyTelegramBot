package com.container;

import com.myAnno.InjectRandomInt;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;

public class InjectRandomIntBeanPostProcessor implements MyBeanPostProcessor {

    @Override
    public void invoke(Object bean) {
        Class<?> beanClass=bean.getClass();
        Field[] fields=beanClass.getDeclaredFields();
        for(Field field:fields){
            InjectRandomInt annotation=field.getAnnotation(InjectRandomInt.class);
            if(annotation!=null){
                Random random=new Random();
                field.setAccessible(true);
                int number=annotation.min()+random.nextInt(annotation.max()-annotation.min());
                ReflectionUtils.setField(field,bean,number);
            }
        }
    }
}
