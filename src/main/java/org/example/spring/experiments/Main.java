package org.example.spring.experiments;

import org.springframework.context.ApplicationContext;

public class Main {
  public static void main(String[] args) {
    System.out.println("Application started!");

    CliControlledLoop loop = new CliControlledLoop();

    ApplicationContext applicationContext = ApplicationContextFactory.createApplicationContext(ApplicationContextType.ANNOTATION);

    while (loop.shouldContinue()) {
      Car car = applicationContext.getBean(Car.class);
      car.start();
    }

    System.out.println("Application ended!");
  }
}
