package com.example.tobi.springtobi.ch03.ex_3_5.calc.template_v2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    public Integer calcSum(String filepath){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filepath));

            while ( (line = br.readLine()) != null ) {
                sum += Integer.parseInt(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Integer sum = 0;
        String line;


        br.close();
        return sum;
    }
    public Integer calcMultiply(String filepath) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filepath));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        }
        Integer sum = 0;
        String line;



        br.close();
        return sum;
    }
}
