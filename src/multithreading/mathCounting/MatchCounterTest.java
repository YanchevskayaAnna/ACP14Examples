package multithreading.mathCounting;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MatchCounterTest {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter base directory:");
        String directory = in.nextLine();
        System.out.print("Enter keyword:");
        String keyword = in.nextLine();
        
        MatchCounter matchCounter = new MatchCounter(new File(directory), keyword);
        FutureTask<Integer> task = new FutureTask(matchCounter);
        Thread t = new Thread(task);
        t.start();
        try {
            System.out.println(task.get() + " matching files.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
