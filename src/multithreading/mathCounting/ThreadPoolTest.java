package multithreading.mathCounting;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.*;

public class ThreadPoolTest {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter base directory:");
        String directory = in.nextLine();
        System.out.print("Enter keyword:");
        String keyword = in.nextLine();

        ExecutorService pool = Executors.newCachedThreadPool();
        MatchCounter matchCounter = new MatchCounter(new File(directory), keyword);
        Future<Integer> result = pool.submit(matchCounter);
        try {
            System.out.println(result.get() + " matching files.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize();
        System.out.println("Largest pool size = " + largestPoolSize);
    }

}
