package com.example.demo.functional;

import java.util.stream.Stream;

public class Calculator {

  public Double add(Double... input) {
    return Stream.of(input).reduce((i, j) -> i + j).orElse(0d);
  }

  public static void main(String[] args) {
    Calculator c = new Calculator();
    System.out.println(c.add(2d, 1d, 3d));
    System.out.println(c.add());
  }
}

