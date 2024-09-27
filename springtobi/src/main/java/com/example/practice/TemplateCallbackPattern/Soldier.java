package com.example.practice.TemplateCallbackPattern;

public class Soldier {
    void runContext(Strategy strategy){
        System.out.println("전투시작");
        strategy.runStrategy();
        System.out.println("전투종료");
    }
    //Client 에서 중복된 코드 가지고 와서 템플릿으로 만들기
    private Strategy executeWeapon(final String weaponSoung){
        return new Strategy() {
            @Override
            public void runStrategy() {
                System.out.println(weaponSoung);
            }
        };
    }
}
