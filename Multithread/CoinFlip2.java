/**
 * Created by Guanzhu Li on 12/1/2016.
 */
/*
Do a similar task (like qn 2), but this time make only one instance of your main class
(the one that implementsRunnable). Still have 5 tasks in the queue.
You are following variation 2 of the basic threadingapproach (main class implementing Runnable).
Now your code will look roughly like this (or, with the calls to execute in a loop):
*/
import java.util.concurrent.*;


public class CoinFlip2 implements Runnable {
    public static void main(String[] args) {
        new CoinFlip2();
    }

    public CoinFlip2() {
        int poolSize = 5;
        ExecutorService tasks = Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < 5; i++) {
            tasks.execute(this);	// executes the overriden 'run' below.
        }
        tasks.shutdown();
    }

    @Override
    public void run() {
        int numHeads = 0;
        for (int i = 0; i < 1000; i++) {
            double coin = Math.random();
            if (coin < 0.5) {
                numHeads++;
            } else {
                numHeads = 0;
            }
            if (numHeads >= 3) {
                System.out.printf("%s got %d heads in a row.%n",
                        Thread.currentThread().getName(), numHeads);
            }
        }
    }
}
