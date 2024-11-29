package org.example.spring.experiments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Car {
  private Engine engine;

  @Autowired
  public Car(Engine engine) {
    this.engine = engine;
  }

  public void start() {
    System.out.println("Car started with engine: " + engine.getEngineType());
  }
}
