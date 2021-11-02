package com.example.carrentservice.models;

import com.example.carrentservice.config.WebConfig;
import lombok.NoArgsConstructor;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Main implements IMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
        IMain im = context.getBean(IMain.class);

        Main m = new Main();
        IMain i = ((IMain)m);

        im.printName("anton");
        i.printName("tehanov");
    }

    @MyAnnotation
    public void printName(String name) {
        System.out.println(name);
    }
}
