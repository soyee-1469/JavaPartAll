package himedia.Practice;

import himedia.Java.A_person;

import java.util.*;

public class A_collections_queue {

    public static void exam1() {
        Queue<String> queue = new LinkedList<String>();
        queue.add("apple");
        queue.add("orange");
        queue.add("banana");

        System.out.println(queue.size()); //3
        System.out.println(queue.peek()); //apple 순서대로 출력
        System.out.println(queue.size()); //3 사이즈변화없음
        System.out.println(queue.poll()); //apple 순서대로 출력
        System.out.println(queue.size()); //2 출력되었던 apple삭제됨
        queue.clear(); //지우기
        System.out.println(queue.size()); //0 싹 지워짐
        System.out.println(queue.isEmpty()); //true 비었는지 boolean

        queue.add("apple");
        queue.add("orange");
        queue.add("banana");

        for (String element : queue) { //element는 queue 데이터를 담는다.
            System.out.println(element);//뭐가들었나
        }

        //Iterator의 장점
        //1. 컬렉션에서 요소를 제어하는 기능
        //2. next() 및 previous()를 써서 앞뒤로 이동하는 기능
        //3. hasNext()를 써서 더 많은 요소가 있는지 확인하는 기능
        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) { //hasNext boolean
            String element = iterator.next();
            System.out.println(element);//참일때 순서대로 출력 apple orange banana
        }


    }

    public static void exam2() {
        Queue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(55);
        priorityQueue.add(99);
        priorityQueue.add(11);
        priorityQueue.add(22);
        System.out.println(priorityQueue.peek()); //11 가장낮은 숫자부터 출력
        System.out.println(priorityQueue.size()); //4 갯수4개
        System.out.println(priorityQueue.poll()); //11 가장 낮은 숫자 출력
        System.out.println(priorityQueue.size()); //3 출력 후 poll에 의해 삭제됨
        System.out.println(priorityQueue.peek()); //22 두번쨰로 낮았던 숫자 출력

        for (Integer element : priorityQueue) {
            System.out.println(element);
        }
        // 22 99 55 순서대로 출력
        Iterator<Integer> iterator = priorityQueue.iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            System.out.println(element);
        }
        // 22 99 55 순서대로 출력

        priorityQueue.forEach(
                element -> System.out.println(element)
        );
        // 22 99 55 순서대로 출력
    }


    public static void main(String[] args) {
        exam2();
    }
}
