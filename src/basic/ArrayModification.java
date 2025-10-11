package basic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayModification {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>(3);
        integers.add(4);
        integers.add(0);
        integers.add(5);
        integers.add(3);

//        removeWithIndex(integers);
        removeWithIter(integers);
    }

    public static void removeWithIndex(List<Integer> integers) {
        for(int i=0; i<integers.size(); i++) {
            System.out.printf("%d, ", i);
            if (i%2==1) {
                integers.remove(i);
            }
        }

        System.out.println(integers);
    }

    private static void removeWithIter(List<Integer> integers) {
        Iterator<Integer> iter = integers.iterator();
        int size = integers.size();
        for (int i=0; i<size; i++) {
            System.out.println(iter.next());
            if (i%2==1) {
                iter.remove();
            }
        }
        System.out.println(integers);
    }
}
