import java.util.Random;

public class ThreadingPlayground {
    public static void main(String[] args) {
        System.out.println("Start");
        System.out.println(Thread.currentThread());
        System.out.println("Middle");
        hello(-1, 100);
        Random rand = new Random();
        long max = 0;
        long milli = (long)(rand.nextDouble() * 1000);
        for (int i = 0; i < 10; i++) {
            final int count = i;
            Thread thread = new Thread(() -> hello(count, milli));
            thread.start();
        }

        System.out.println(Thread.currentThread());
        System.out.println("End");
    }

    static void hello(int thread, long milliseconds) {
        try {
            Thread.sleep(milliseconds);
            System.out.println("Slept for: " + milliseconds);
            System.out.println(Thread.currentThread());
            System.out.println("Hello from hello(" + thread + ")");
        } catch (InterruptedException ie) {
            System.out.println("Interrupted.");
        }
    }
}
