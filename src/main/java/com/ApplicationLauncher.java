package com;

import com.commands.*;
import com.container.Container;
import com.entity.City;
import com.myAnno.InjectRandomInt;
import com.myAnno.RandomInjectInvoker;
import com.service.serviceImpl.CityServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.ApiContextInitializer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

@SpringBootApplication
public class ApplicationLauncher implements CommandLineRunner {




    public static void main(String[] args){
        ApiContextInitializer.init();
        SpringApplication.run(ApplicationLauncher.class,args);
    }

    @Bean(name = "string")
    public String mudak(){
        return "fgdg";
    }

    private void number(int c){
        c=4000;

    }
    private void cser(CityServiceImpl service){
        CityServiceImpl service1=new CityServiceImpl("dfgdgf");
        service=service1;

    }

    @Override
    public void run(String... args) throws Exception {
       int c=30;
       number(c);
       System.out.println(c);
       CityServiceImpl cityService=new CityServiceImpl("");
       cser(cityService);
       System.out.println(cityService);
       Container container=new Container();
       List<Object> list=new ArrayList<>();
       list.add(new NotificationAllCommand());
       list.add(new ReverseWordCommand());
       container.setBeans(list);
       container.performInject();
       System.out.println(container.getBeans());
        Collection<String> collection=new ArrayList<>();
        collection.add("dfg");
        collection.add("dfgg");
        Iterator<String> iterator=collection.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
//        Class<?> someClass=Class.forName("com.bot.ChatBot");
//        System.out.println(someClass.isInterface());
//        Method[] methods=someClass.getMethods();
//        for(Method method :methods){
//            System.out.println("Method name is "+method);
//            Annotation[] methodAnnotation=method.getDeclaredAnnotations();
//            for(Annotation annotation:methodAnnotation){
//                System.out.println("Method annotation is"+ annotation);
//            }
//        }
//        Field[] fields=someClass.getDeclaredFields();
//        for(Field field:fields){
//            System.out.println("Field name is "+field);
//            Annotation[] fieldAnnotation=field.getDeclaredAnnotations();
//            for(Annotation annotation:fieldAnnotation){
//                if(annotation.annotationType().equals(Class.forName("org.springframework.beans.factory.annotation.Autowired"))){
//                    System.out.println("autowired!");
//                }
//            }
//        }
//        Annotation[] annotations=someClass.getDeclaredAnnotations();
//        for(Annotation annotation:annotations){
//            System.out.println("Annotation is "+annotation);
//            System.out.println("Annotation type is "+annotation.annotationType());
//        }

    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public Map<String,Class<? extends Command>> map(){
        Map<String,Class<? extends Command>> actions=new HashMap<>();
        actions.put("/cities", CityCommand.class);
        actions.put("/reverse", CityReverseCommand.class);
        actions.put("/sort", CitySortByNameCommand.class);
        actions.put("/reverse cities", ReverseWordCommand.class);
        actions.put("/notificationAll", NotificationAllCommand.class);
        return actions;
    }
}
