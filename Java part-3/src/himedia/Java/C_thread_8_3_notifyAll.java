package himedia.Java;
/*
 * 기아 현상(Starvation)
 멀티스레딩 프로그래밍에서 특정 스레드가 다른 스레드들에 의해 지속적으로 자원을 얻지 못해
 실행되지 못하는 상황을 의미한다.
 이 문제를 해결하는 방법 중 하나로, notifyAll()을 사용할 수 있다.
 notifyAll() 대기 중인 모든 스레드를 깨워서 자원을 경쟁하도록 한다.
 반면에 notify()는 대기 중인 스레드 중 하나만 깨우기 때문에 기아 현상이 발생할 가능성이 크다.

 스레드들 간에 자원을 고르게 배분하지 않고 notify()만 사용하여 특정 스레드가
 자원을 계속 얻지 못하는 상황을 시뮬레이션하고, notifyAll()을 사용해 기아 현상을 해결.

뮤텍스(Mutex) 란?
상호배제라고도 한다. 임계 영역을 가진 쓰레드들의 Running Time이 서로 겹치지 않게 각각 단독으로 실행하게 하는 기술이다.
 다중 프로세스들의 공유 리소스에 대한 접근을 조율하기 위해 locking과 unlocking을 사용한다.
 즉, 쉽게 말하면 뮤텍스 객체를 두 쓰레드가 동시에 이용할 수 없다는 의미이다

세마포어는 리소스의 상태를 나타내는 간단한 카운터로 생각할 수 있다. 일반적으로 비교적 긴 시간을 확보하는 리소스에 대해 이용하게 되며,
 유닉스 시스템의 프로그래밍에서 세마포어는 운영체제의 리소스를 경쟁적으로 사용하는 다중 프로세스에서 행동을 조정하거나 또는 동기화 시키는 기술이다.
현재 공유자원에 접근할 수 있는 쓰레드,프로세스의 수를 나타내는 값을 두어 상호배제를 달성하는 기법이다.

세마포어와 뮤텍스의 차이점
세마포어는 뮤텍스가 될 수 있지만 뮤텍스는 세마포어가 될 수 없다.
세마포어는 소유할 수 없는 반면, 뮤텍스는 소유가 가능하며 소유주가 이에 대한 책임을 진다.
뮤텍스의 경우 뮤텍스를 소유하고 있는 쓰레드가 이 뮤텍스를 해제할 수 있다. 하지만 세마포어의 경우 이러한 세마포어를 소유하지 않는 쓰레드가 세마포어를 해제할 수 있다.
세마포어는 시스템 범위에 걸쳐있고 파일시스템상의 파일 형태로 존재한다. 반면 뮤텍스는 프로세스 범위를 가지며 프로세스가 종료될 때 자동으로 Clean up 된다.
가장 큰 차이점은 관리하는 동기화 대상의 갯수이다.
뮤텍스는 동기화 대상이 오직 하나뿐일 때, 세마포어는 동기화 대상이 하나 이상일 때 사용한다.

예시를 들면//
뮤텍스는 화장실이 하나 뿐인 없는 식당과 비슷하고 화장실을 가기 위해서는 카운터에서 열쇠를 받아 가야한다.//
세마포어는 여러 개의 화장실 칸을 가진 레스토랑이다. 화장실 입구에는 현재 화장실의 빈 칸 개수를 보여주는 전광판이 있다.
 전광판에 빈 칸이 1개 이상이면 사용이 가능하고 0개이면 대기해야된다.
*/


class SharedResource {

    private boolean isAvailable = false;

    // 자원을 대기하는 메서드
    public synchronized void waitForResource(String threaName) {
        while (!isAvailable) {
            try {
                System.out.println(threaName + " is waiting for resource...");
                wait(); // 자원이 사용 가능할 때까지 대기
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(threaName + " got the resource!");
        isAvailable = false;
    }

    // 자원을 공급하는 메서드
    public synchronized void makeResourceAvailable() {
        isAvailable = true;
        System.out.println("Resource is now available!");
//        notify();
        notifyAll();
    }

}

class WorkerThread extends Thread {
    private SharedResource sharedResource;
    private String threadName;

    public WorkerThread(SharedResource sharedResource, String threadName) {
        this.sharedResource = sharedResource;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        while (true) {
            sharedResource.waitForResource(threadName);
            try {
                Thread.sleep(1000); // 자원을 얻은 후 1초간 작업 수행
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class C_thread_8_3_notifyAll {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        WorkerThread worker1 = new WorkerThread(sharedResource, "worker1");
        WorkerThread worker2 = new WorkerThread(sharedResource, "worker2");
        WorkerThread worker3 = new WorkerThread(sharedResource, "worker3");

        worker1.start();
        worker2.start();
        worker3.start();

        // 2초마다 자원을 공급
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    sharedResource.makeResourceAvailable();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        ).start();

    }
}