package com.example.practice.StrategyPattern;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Client {
    public static void main(String[] args) {
        setCharacter();
        Strategy strategy = null;
        Soldier rambo = new Soldier();

        strategy = new StrategyGun();
        rambo.runContext(strategy);

        System.out.println();

        strategy = new StrategySword();
        rambo.runContext(strategy);

        System.out.println();

        strategy = new StrategyBow();
        rambo.runContext(strategy);
    }
    private static void setCharacter() {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
