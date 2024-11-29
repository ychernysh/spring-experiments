package org.example.spring.experiments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
@Scope("prototype")
public class Engine {
  private String engineType;

  @Autowired
  public Engine(Supplier<String> engineTypeSupplier) {
    this.engineType = engineTypeSupplier.get();
  }

  public String getEngineType() {
    return engineType;
  }
}
