package com.myAnno;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Random;

@Component
public class InjectRandomIntBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
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
        return bean;
    }
}
