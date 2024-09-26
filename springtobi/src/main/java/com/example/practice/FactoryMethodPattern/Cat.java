package com.example.tobi.practice.FactoryMethodPattern;

public class Cat extends Animal {
    @Override
    AnimalToy getToy() {
        return new CatToy();
    }
}
