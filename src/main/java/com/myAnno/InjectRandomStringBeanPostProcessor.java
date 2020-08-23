package com.myAnno;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Component
public class InjectRandomStringBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass=bean.getClass();
        Field[] fields=beanClass.getDeclaredFields();
        for(Field  field: fields){
            InjectRandomString randomString=field.getAnnotation(InjectRandomString.class);
            if(randomString!=null){
                field.setAccessible(true);
                ReflectionUtils.setField(field,bean,randomString.mat1()+" "+randomString.mat2());
            }
        }
        return bean;
    }
}
