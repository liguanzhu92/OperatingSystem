import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Guanzhu Li on 12/1/2016.
 */

/*      Make a coin-flipping class that implements Runnable. The run method should flip 1000 coins
        and print out whenever it sees 3 or more consecutive heads. Make a task queue, and put 5 separate
        instances of the Runnable class in the queue. In the printouts, you can use Thread.current-
        Thread().getName() to identify the thread. You are following variation 1 of the basic threading
        approach (separate classes that implement Runnable), so your code will look something like this
        (or, you could call execute from a loop):*/
public class CoinFlip implements Runnable{
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
    public static void main(String args[]) {
        ExecutorService tasks = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            tasks.execute(new CoinFlip());	// invokes CoinFlipper run() method
        }
        tasks.shutdown();
    }
}

