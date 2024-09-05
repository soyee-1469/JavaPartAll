package himedia.Java;

// * Queue
// 먼저 삽입된 요소가 먼저 제거되는 선입선출(FIFO : First In First Out) 구조를 따르는 자료구조이다.
// Queue 인터페이스는 자바에서 큐의 동작을 정의하며, 다양한 구현체가 있다.
// 대표적인 구현체로는 LinkedList, PriorityQueue, ArrayQueue 등이 있다.

// * 주요 메서드
// - add(E e) : 큐의 맨 뒤에 요소를 추가합니다. 큐가 가득 찬 경우 예외를 발생시킨다.
// - offer(E e) : 큐의 맨 뒤에 요소를 추가합니다. 큐가 가득 차도 예외를 발생시키지 않고 false를 반환한다.
// - remove() : 큐의 맨 앞에 있는 요소를 제거하고 반환한다. 큐가 비어 있는 경우 예외를 발생 시킨다.
// - poll() : 큐의 맨 앞에 있는 요소를 제거하고 반환한다. 큐가 비어 있으면 null을 반환한다.
// - element() : 큐의 맨 앞에 있는 요소를 반환하지만, 제거하지는 않는다. 큐가 비어 있는 경우 예외를 발생 시킨다.
// - peek() : 큐의 맨 앞에 있는 요소를 반환하지만, 제거하지는 않는다. 큐가 비어 있으면 null을 반환한다.

import java.util.*;


class Graph{
    private LinkedList<Integer>[] grahpList;
    public Graph(int vertexCount){
        this.grahpList = new LinkedList[vertexCount +1];
        //각 인접 리스트 초기화
        for (int i = 0; i < grahpList.length; i++) {
            grahpList[i] = new LinkedList<>();
        }
    }
   //간선추가 정접v 인접w 정접1:인접 2,3
    public void addEdge(int v, int w){
        grahpList[v].add(w);
        grahpList[w].add(v);
    }

    public LinkedList<Integer>[] getGrahpList() {
        return grahpList;
    }
}
public class A_collections_queue {

    // 1. LinkedList
    // - LinkedList는 Queue인터페이스를 구현하므로, 큐로 사용할 수 있다.
    // - LinkedList는 양방향 연결 리스트로, 큐와 리스트 모두 사용할 수 있다.
    public static void exam1() {
        Queue<String> queue = new LinkedList<>();

        // 요소 추가
        queue.add("Apple");
        queue.add("Banana");
        queue.add("Orange");

        // Peek : 큐의 맨 앞 요소 확인(제거하지 않음)
        System.out.println("size before: " + queue.size());
        System.out.println("Peek : " + queue.peek());
        System.out.println("size after: " + queue.size());

        // Poll : 큐에서 요소 제거 및 반환
        System.out.println("size before: " + queue.size());
        System.out.println("Poll : " + queue.poll());
        System.out.println("size after: " + queue.size());
        System.out.println("Poll : " + queue.poll());

        // Clear : 큐 비우기
        queue.clear();
        System.out.println("Queue is empty : " + queue.isEmpty());

        // 요소 추가
        queue.add("Apple");
        queue.add("Banana");
        queue.add("Orange");

        // 순회 방법 1
        for ( String element : queue ) {
            System.out.println("Element : " + element);
        }
        // Apple Banana Orange

        // 순회 방법 2
        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println("Element : " + element);
        }
        // Apple Banana Orange
    }

    // 2. PriorityQueue
    // - 요소가 자연 순서(기본 순서)나 제공된 비교기(Comparator)에 따라 결정된다.
    // - FIFO 순서가 아니라 우선순위에 따라 요소가 처리된다.
    public static void exam2() {
        Queue<Integer> priorityQueue = new PriorityQueue<>();

        // 요소추가
        priorityQueue.add(50);
        priorityQueue.add(20);
        priorityQueue.add(30);
        priorityQueue.add(10);

        // Peek
        System.out.println("Peek : " + priorityQueue.peek());

        // Poll
        System.out.println("Poll : " + priorityQueue.poll());

        // 순회 방법1
        for (Integer element : priorityQueue) {
            System.out.println("Element : " + element);
        }

        // 순회 방법2
        Iterator<Integer> iterator = priorityQueue.iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            System.out.println("Element : " + element);
        }

        // 순회 방법3
        priorityQueue.forEach(
                element -> System.out.println("Element : " + element)
        );

    }

    public static void exam2_2() {
        // 나이를 기준으로 오름차순 정렬하는 우선순위 큐
        Comparator<A_person> ageComp = new Comparator<A_person>() {
            @Override
            public int compare(A_person o1, A_person o2) {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        };

        Queue<A_person> priorityQueue = new PriorityQueue<>(ageComp);

        // 요소추가
        priorityQueue.add(new A_person("Alice", 20));
        priorityQueue.add(new A_person("Bob", 30));
        priorityQueue.add(new A_person("Charlie", 10));
        priorityQueue.add(new A_person("David", 40));

        System.out.println("Peek : " + priorityQueue.peek());

//        for (A_person element : priorityQueue) {
//            System.out.println("Element : " + element);
//        }

        while (!priorityQueue.isEmpty()) {
            A_person element = priorityQueue.poll();
            System.out.println("Element : " + element);
        }
        // Priority Queue는 큐에서 요소를 꺼낼 때만 우선순위에 따라 요소를 반환한다.
        // 따라서 내부적으로 유지되는 우선순위에 따라 큐에서 하나씩 꺼내면서 요소를 확인해야 한다.
        // 반복문으로 순회할 때는 이 순서가 보장되지 않는다.
        // 반드시 poll()이나 remove()와 같은 메서드를 사용해 요소를 꺼내면서 확인해야한다.
    }

    public static void exam2_3() {
        Comparator<A_person> ageComp = new Comparator<A_person>() {
            @Override
            public int compare(A_person o1, A_person o2) {
                return Integer.compare(o2.getAge(), o1.getAge());
            }
        };

        Queue<A_person> priorityQueue = new PriorityQueue<>(ageComp);

        // 요소추가
        priorityQueue.add(new A_person("Alice", 20));
        priorityQueue.add(new A_person("Bob", 30));
        priorityQueue.add(new A_person("Charlie", 10));
        priorityQueue.add(new A_person("David", 40));

        while (!priorityQueue.isEmpty()) {
            A_person element = priorityQueue.poll();
            System.out.println("Element : " + element);
        }
    }

    public static void exam2_4() {
        // 이름으로 오름차순
        Comparator<A_person> nameComp = new Comparator<A_person>() {
            @Override
            public int compare(A_person o1, A_person o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        Queue<A_person> priorityQueue = new PriorityQueue<>(nameComp);

        // 요소추가
        priorityQueue.add(new A_person("Alice", 20));
        priorityQueue.add(new A_person("Bob", 30));
        priorityQueue.add(new A_person("Charlie", 10));
        priorityQueue.add(new A_person("David", 40));

        while (!priorityQueue.isEmpty()) {
            A_person element = priorityQueue.poll();
            System.out.println("Element : " + element);
        }
    }

    public static void exam2_5() {
        Comparator<A_person> nameComp = new Comparator<A_person>() {
            @Override
            public int compare(A_person o1, A_person o2) {
                return o2.getName().compareTo(o1.getName());
            }
        };

        Queue<A_person> priorityQueue = new PriorityQueue<>(nameComp);

        // 요소추가
        priorityQueue.add(new A_person("Alice", 20));
        priorityQueue.add(new A_person("Bob", 30));
        priorityQueue.add(new A_person("Charlie", 10));
        priorityQueue.add(new A_person("David", 40));

        while (!priorityQueue.isEmpty()) {
            A_person element = priorityQueue.poll();
            System.out.println("Element : " + element);
        }
    }

    public static void exam5(){
        boolean[] visited = new boolean[9+1];
        //기본형boolean은 초기화 안해도 값 false 참조형(Boolean 앞자리 대문자) 는 null값이 들어가있음
        Graph graph = new Graph(9);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 6);
        graph.addEdge(3, 7);
        graph.addEdge(4, 5);
        graph.addEdge(4, 7);
        graph.addEdge(4, 8);
        graph.addEdge(5, 6);
        graph.addEdge(7, 8);
        graph.addEdge(8, 9);
        //인접리스트

        //탐색
        int startVertex = 1;
        Queue<Integer> queue = new LinkedList<>();
        visited[startVertex] = true;
        queue.add(startVertex);
        while (queue.size()>0) {
            int vertex = queue.poll();
            System.out.print(vertex+ " -> ");


            //현재 정점의 인접 정점들 중 장문하지 않은 정점을 모두 큐에 추가
            for(int w : graph.getGrahpList()[vertex]){
                if(!visited[w]){
                    queue.add(w);
                    visited[w] = true;
                }
            }
        }

    }
    public static void main(String[] args) {
        exam5();
    }
}