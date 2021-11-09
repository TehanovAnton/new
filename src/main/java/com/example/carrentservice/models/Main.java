package com.example.carrentservice.models;

import com.example.carrentservice.config.WebConfig;
import lombok.NoArgsConstructor;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
        Main m = context.getBean(Main.class);
        Main n = new Main();

        m.printName("anton");
        n.printName("tehanov");
    }

    @MyAnnotation
    public void printName(String name) {
        System.out.println(name);
    }
}
