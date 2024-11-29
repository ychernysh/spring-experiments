package org.example.spring.experiments;

import java.io.PrintStream;
import java.util.Scanner;

public class CliControlledLoop {

  private final PrintStream out = System.out;
  private final Scanner in = new Scanner(System.in);

  public boolean shouldContinue() {
    out.println("Should continue (no / any text)?");
    String answer = in.nextLine();
    return !"no".equals(answer);
  }
}
