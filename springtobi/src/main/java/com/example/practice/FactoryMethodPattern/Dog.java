package com.example.practice.FactoryMethodPattern;

public class Dog extends Animal {

    @Override
    AnimalToy getToy() {
        return new DogToy();
    }
}
