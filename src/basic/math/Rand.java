package basic.math;

import java.util.Random;

public class Rand {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i=0; i<20; i++) {
            System.out.printf("%d,", random.nextInt(0, 3));;
        }
    }
}
