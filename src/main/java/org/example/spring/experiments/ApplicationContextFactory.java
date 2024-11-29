package org.example.spring.experiments;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextFactory {
  public static ApplicationContext createApplicationContext(ApplicationContextType type) {
    switch (type) {
      case ANNOTATION:
        return new AnnotationConfigApplicationContext(AppConfig.class);
      case XML:
      default:
        return new ClassPathXmlApplicationContext("applicationContext1.xml", "applicationContext2.xml");
    }
  }
}
