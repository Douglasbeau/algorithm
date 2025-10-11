package basic.thread;

import java.util.concurrent.TimeUnit;
// 在构造器启动线程
public class StartInConstructor extends Thread{
    private int countDown = 5;
    public StartInConstructor() {
        System.out.println(this + " created");
        start();
    }

    @Override
    public void run() {
        System.out.println(this + "started ");

        while (countDown > 0){
//            try {
//                TimeUnit.MICROSECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println(this.toString() + countDown-- + ";");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
            new StartInConstructor();
    }
}
