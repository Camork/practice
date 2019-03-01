package concurrency;

public class TestMemoryBarrier {
    boolean running = false;

    boolean get() {
        //VolatileClass aClass = new VolatileClass();
        return running;
    }

    synchronized void doSetTrue() {
        running = true;
    }

    public static void main(String[] args) throws InterruptedException {
        TestMemoryBarrier instance = new TestMemoryBarrier();

        new Thread(
            () -> {
                while (!instance.get()) {
                }

                System.out.println("Thread 1 finished.");
            }).start();

        Thread.sleep(1000);

        new Thread(
            () -> {
                instance.doSetTrue();

               System.out.println("Thread 2 finished.");
            }).start();
    }
}

class VolatileClass {
    private volatile int a;
}