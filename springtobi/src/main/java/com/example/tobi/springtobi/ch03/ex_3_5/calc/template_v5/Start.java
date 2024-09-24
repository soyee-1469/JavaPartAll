package com.example.tobi.springtobi.ch03.ex_3_5.calc.template_v5;

import java.io.File;
import java.io.IOException;

public class Start {
    public static void main(String[] args) throws IOException {
        Start start = new Start();
        start.sumOfNumbers();
        start.multiplyOfNumbers();
        start.divideOfNumbers();
        start.minusOfNumbers();
    }

    public void sumOfNumbers() throws IOException {
        Calculator calculator = new Calculator();

        String userHome = System.getProperty("user.home");
        String desktop = userHome + File.separator + "Desktop";
        String filepath = desktop + File.separator + "java" + File.separator + "numbers.txt";
        int sum = calculator.calcSum(filepath);
        System.out.println(sum);
    }

    public void multiplyOfNumbers() throws IOException {
        Calculator calculator = new Calculator();
        String userHome = System.getProperty("user.home");
        String desktop = userHome + File.separator + "Desktop";
        String filepath = desktop + File.separator + "java" + File.separator + "numbers.txt";
        Double multiply = calculator.calcMultiply(filepath);
        System.out.println(multiply);
    }

    public void divideOfNumbers() throws IOException {
        Calculator calculator = new Calculator();
        String userHome = System.getProperty("user.home");
        String desktop = userHome + File.separator + "Desktop";
        String filepath = desktop + File.separator + "java" + File.separator + "numbers.txt";
        Float divide = calculator.calcDivide(filepath);
        System.out.println(divide);
    }

    public void minusOfNumbers() throws IOException {
        Calculator calculator = new Calculator();
        String userHome = System.getProperty("user.home");
        String desktop = userHome + File.separator + "Desktop";
        String filepath = desktop + File.separator + "java" + File.separator + "numbers.txt";
        Integer minus = calculator.calcMinus(filepath);
        System.out.println(minus);
    }

}