package com.example.tobi.practice.FactoryMethodPattern;

public class Dog extends Animal {

    @Override
    AnimalToy getToy() {
        return new DogToy();
    }
}
