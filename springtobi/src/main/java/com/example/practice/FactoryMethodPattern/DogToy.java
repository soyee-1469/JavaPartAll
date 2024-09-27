package com.example.practice.FactoryMethodPattern;

public class DogToy extends AnimalToy {
    @Override
    void identify() {
        System.out.println("나는 테니스공! 강아지친구");
    }
}
