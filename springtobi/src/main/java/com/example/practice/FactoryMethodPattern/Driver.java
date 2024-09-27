package com.example.practice.FactoryMethodPattern;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
/*
-------------------------------------------------------------
public abstract class Animal {
    abstract AnimalToy getToy();}
-------------------------------------------------------------
public abstract class AnimalToy {
    abstract void identify();}
-------------------------------------------------------------
public class Cat extends Animal {
    @Override
    AnimalToy getToy() {
        return new CatToy(); }}
-------------------------------------------------------------
public class CatToy extends AnimalToy{
    @Override
    void identify() { System.out.println("나는 캣타워 고양이의 친구");}}
-------------------------------------------------------------
public class Dog extends Animal {
    @Override
    AnimalToy getToy() { return new DogToy(); }}
-------------------------------------------------------------
public class DogToy extends AnimalToy {
    @Override
    void identify() {System.out.println("나는 테니스공! 강아지친구");}}
-------------------------------------------------------------
 */
public class Driver {
    public static void main(String[] args) {
        setCharacter();
        Animal bolt = new Dog(); //getToy
        Animal kitty = new Cat(); // getToy

        AnimalToy boltBall = bolt.getToy();
        AnimalToy kittyTower = kitty.getToy();

        boltBall.identify(); //dogToy
        kittyTower.identify(); //catToy
    }
    private static void setCharacter() {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
