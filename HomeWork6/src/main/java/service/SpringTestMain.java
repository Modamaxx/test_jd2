package service;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.api.IEmployerService;

import java.io.IOException;

public class SpringTestMain {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("service.xml","storage.xml");

        IEmployerService employerService =context.getBean(IEmployerService.class);
        employerService.generationEmployers();



    }
}
