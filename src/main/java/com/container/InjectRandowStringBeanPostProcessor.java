package com.container;

import com.myAnno.InjectRandomString;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class InjectRandowStringBeanPostProcessor implements MyBeanPostProcessor {

    @Override
    public void invoke(Object bean) {
        Class<?> beanClass=bean.getClass();
        Field[] fields=beanClass.getDeclaredFields();
        for(Field  field: fields){
            InjectRandomString randomString=field.getAnnotation(InjectRandomString.class);
            if(randomString!=null){
                field.setAccessible(true);
                ReflectionUtils.setField(field,bean,randomString.mat1()+" "+randomString.mat2());
            }
        }
    }
}
