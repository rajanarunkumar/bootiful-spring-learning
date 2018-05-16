package com.example.demo.inheritance;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InheriterWithModifyMood extends BaseClass {

  private final String someString;

  public InheriterWithModifyMood(String someString) {
    super(someString);
    this.someString = someString.concat(" & Some Addon");
  }

  @Override
  public String getSomeString() {
    return someString;
  }

  public static void main(String[] args) {
    BaseClass base = new InheriterWithModifyMood("Hello World!");
    log.info("Printing someString: {} ", base.getSomeString());
  }
}
