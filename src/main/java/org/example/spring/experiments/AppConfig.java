package org.example.spring.experiments;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

@Configuration
@PropertySource("classpath:config.properties")
@Lazy
@ComponentScan(basePackages = "org.example.spring.experiments")
public class AppConfig {

  @Value("${engine.type.supplier.class.name}")
  private String supplierClassName;

  @Bean
  public Supplier<String> engineTypeSupplier() {
    try {
      return (Supplier<String>) Class.forName(supplierClassName).getDeclaredConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
