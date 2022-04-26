import java.util.Arrays;

public class Parallel {
    static double totalTime = 0;
    public static void main(String[] args) {
        Runnable doSort = () -> {
            long start = System.nanoTime();
            sort(10000);
            start = System.nanoTime() - start;
            totalTime += start;
            System.out.println("Time to finish sorting for this thread: " + start);
            System.out.println("Total time for all threads: " + (totalTime / 1000000000.0));
        };
        Thread thread1 = new Thread(doSort);
        Thread thread2 = new Thread(doSort);
        Thread thread3 = new Thread(doSort);
        Thread thread4 = new Thread(doSort);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    static void sort(int count) {
        double [] nums = new double[count];
        for (int i = 0; i < nums.length;i++){
            nums[i] = Math.random();
        }

        Arrays.sort(nums);
    }
}
