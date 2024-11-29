package org.example.spring.experiments;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Supplier;

@Component
@Scope("prototype")
public class CliPromptSupplier implements Supplier<String> {

  private final PrintStream out = System.out;
  private final Scanner in = new Scanner(System.in);

  @Override
  public String get() {
    out.print("Please supply a string value: ");
    String value = in.nextLine();
    return value;
  }
}
