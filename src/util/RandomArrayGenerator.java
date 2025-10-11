package util;

import java.util.Arrays;
import java.util.Random;

// 随机整数数组生成器
public class RandomArrayGenerator {
    public static int[] generate(int length, int max) {

        if (length<0 || max<0) {
            System.out.println("error: length and max should be larger than 0");
            throw new RuntimeException("length and max should be larger than 0");
        }

        int[] arr = new int[length];
        for (int i=0; i<length; i++){
            arr[i] = (int)(Math.random() * (max + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] generate = generate(4, 30000);
        System.out.println(Arrays.toString(generate));
    }
}
