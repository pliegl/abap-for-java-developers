package com.ecosio.functiongroup;

import java.util.LinkedList;
import java.util.List;

/**
 * Simple Java class to demonstrate the application of the logic
 * in ABAP
 *
 * Nota bene: the naming of the class and the methods is not Java
 * best practice, but has been chosen
 * to match the naming in ABAP
 */
public class Z_FUNCGRP {


  /**
   * The number analyzer takes two input numbers (passed as String)
   * as well as a counter instance.
   *
   * The counter is incremented with every analysis operation.
   *
   * The analysis returns the sum of the two numbers and if the
   * parameters are even or odd.
   *
   * Since Java does not allow for multiple method return values,
   * we use an object list as return value
   *
   * Nota bene: This method is for demonstration purposes only.
   * In a real world sample one would not use String parameters
   * for integer values, one would probably split the sum operation
   * and the analysis operation into two methods, one would not pass
   * the counter instance but increment at the caller's side etc.
   *
   * @param input_number_1 Integer parameter 1 as String
   * @param input_number_2 Integer parameter 2 as String
   * @param counter an instance of a counter
   * @return
   */
  public LinkedList Z_NUMBER_ANALYZER(String input_number_1,
                                      String input_number_2, Counter counter)
                                      throws NumberFormatException {

    LinkedList analysisResult = new LinkedList();

    Integer sum = 0;
    String result = ""; //One should use a StringBuffer here, but for
    // the demo we take String
    Integer input_1, input_2;

    //The two method parameters should be integer to avoid this conversion,
    // but for the demo we take String
    input_1 = Integer.valueOf(input_number_1);
    input_2 = Integer.valueOf(input_number_2);
    sum = input_1 + input_2;
    analysisResult.add(sum);

    int i = counter.getValue();
    counter.setValue(i++);

    String s = Z_IS_EVEN_FORM(input_1);
    result = "Input parameter 1 is " + input_1 + " and " + s + ".";

    s = Z_IS_EVEN_FORM(input_2);
    result += " Input parameter 2 is " + input_2 + " and " + s + ".";
    analysisResult.add(result);

    return analysisResult;
  }

  /**
   * Determines if a number is even or odd
   * @param form_input The number to be checked
   * @return String value 'even' or 'odd'
   */
  private String Z_IS_EVEN_FORM (int form_input) {

    if ((form_input % 2) == 0) {
      return "even";
    }
    else {
      return "odd";
    }

  }

  public void run() {
    //Note, that we cannot simply pass an Integer to the method and alter
    // it in the method, since Integer is an immutable object reference
    // (like String, BigDecimal, etc.). Thus, we use a helper construct here
    Counter counter = new Counter();

    try {
      List result = Z_NUMBER_ANALYZER("3", "6",
                                      counter);

      //Since the return list contains untyped objects, we need to cast
      // them back to the original type
      String s = (String)result.get(1);
      System.out.println("Number analysis returns: " + s);

      Integer sum = (Integer)result.get(0);
      System.out.println("Sum of numbers is: " + sum);

      System.out.println("Counter is: " + counter.getValue());



    }
    catch (Exception e) {
      System.err.println("Unable to analyse numbers - invalid input data type.");
    }
  }


  public static void main (String [] args) {
    Z_FUNCGRP test = new Z_FUNCGRP();
    test.run();
  }


  /**
   * Dummy class for increasing the counter
   */
  private class Counter {
    private Integer value = 1;

    public Integer getValue() {
      return value;
    }

    public void setValue(Integer value) {
      this.value = value;
    }
  }




}
