package com.container;

import com.commands.NotificationAllCommand;
import com.entity.User;

import java.util.ArrayList;
import java.util.List;

public class Container {

    List<Object> beans;

    List<MyBeanPostProcessor> beanPostProcessors;

    public Container() {
        beans=new ArrayList<>();
        beans.add(new User());
        init();
    }

    private void init(){
        beanPostProcessors=new ArrayList<>();
        beanPostProcessors.add(new InjectRandomIntBeanPostProcessor());
        beanPostProcessors.add(new InjectRandowStringBeanPostProcessor());

    }

    public void performInject(){
        for(MyBeanPostProcessor beanPostProcessor:beanPostProcessors){
            for(Object bean:beans){
                beanPostProcessor.invoke(bean);
            }
        }
    }

    public List<Object> getBeans() {
        return beans;
    }

    public void setBeans(List<Object> beans) {
        this.beans = beans;
    }
}
