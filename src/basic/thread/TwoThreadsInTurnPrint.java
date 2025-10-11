package basic.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 两个线程交替打印
public class TwoThreadsInTurnPrint {

    public static void main(String[] args) {
        Printer printer = new Printer();
        ExecutorService executorService = Executors.newCachedThreadPool();
        int N = 26;
        abstract class RunTemplate implements Runnable{
            abstract void method () throws InterruptedException;
            @Override
            public void run() {
                try {
                    for (int i=0; i<N && !Thread.interrupted(); i++){
                        method();
                    }
                    System.out.printf("\n%s thread exit 0\n", Thread.currentThread());
                } catch (InterruptedException e) {
                    System.out.println("thread interrupted");
                }
            }
        }
        executorService.execute(new RunTemplate() {
            @Override
            void method() throws InterruptedException {
                printer.printNum();
                printer.waitingForChar();
            }
        });
        executorService.execute(new RunTemplate() {
            @Override
            void method() throws InterruptedException {
                printer.waitingForNum();
                printer.printChar();
            }
        });
        executorService.shutdown();
    }

    private static class Printer{
        boolean numTurn = true;
        int n = 1;
        int c = 'a';
        synchronized void printNum(){
            System.out.printf("%d ", n++);
            numTurn = false;
            notify();
        }
        synchronized void waitingForChar() throws InterruptedException{
            while (!numTurn)
                wait();
        }

        synchronized void printChar(){
            System.out.printf("%c ", c++);
            numTurn = true;
            notify();
        }
        synchronized void waitingForNum() throws InterruptedException{
            while (numTurn)
                wait();
        }
    }
}

