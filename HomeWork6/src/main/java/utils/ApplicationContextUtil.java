package utils;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextUtil {
    private static AnnotationConfigApplicationContext context;

    static {
        context = new AnnotationConfigApplicationContext("config");

    }

    public static AnnotationConfigApplicationContext getContext() {
        return context;
    }
    public void close(){
        context.close();
    }
}
