package org.example.spring.experiments;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.Supplier;

@Component
@Scope("prototype")
public class FileSupplier implements Supplier<String> {
  @Override
  public String get() {
    try {
      return new Scanner(new FileInputStream("engine-type.txt")).nextLine();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return "error";
  }
}
