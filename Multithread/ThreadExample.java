import java.util.Scanner;


/**
 * Created by Guanzhu Li on 12/1/2016.
 */

    /*Write a  program counts the number of prime integers between 3001 and 6000.
            Note : The work is divided among one to five threads.  The number of threads is chosen by the user.*/

public class ThreadExample extends Thread{
    int count;
    int start;
    int end;
    public ThreadExample (int start, int end) {
        this.start = start;
        this.end = end;
    }
    public void run(){
        for (int i = start; i < end; i++) {
            if ((ThreadExample.isPrime(i))) {
                count++;
                System.out.println(i);
                System.out.println(Thread.currentThread().getName());
            }
        }

    }

    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        double limit = Math.sqrt(n);
        for (long i = 2; i <= limit; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        ThreadExample[] pThreads = new ThreadExample[n];
        int base = 3000/n;
        for (int i = 0; i < n; i++) {
            int start = 3001 + base * i;
            int end = 3000 + base * (i + 1);
            pThreads[i] =  new ThreadExample(start, end);
            pThreads[i].start();
        }
        try {
            for (int i = 0; i < n; i++)
                pThreads[i].join();
        } catch (InterruptedException e) {
        }
        System.out.println("\nThreads have finished");
        int NumPrimes = 0;
        for (int i = 0; i < n; i++){
            NumPrimes += pThreads[i].count;
        }
        System.out.println(" Number of primes is: " + NumPrimes);
    }
}
