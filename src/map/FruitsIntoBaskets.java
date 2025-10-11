package map;

import array.FruitIntoBask;

import java.util.Map;
import java.util.TreeMap;

public class FruitsIntoBaskets {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        int ans = 0;
        TreeMap<Integer, Integer> spaceCnt = new TreeMap<>();
        int i = 0;
        int j = 0;
        while (i < n) {
            int f = fruits[i];
            Map.Entry<Integer, Integer> left = spaceCnt.ceilingEntry(f);
            if (left == null) { // 左边找不到
                int space;
                while (j < n && f > baskets[j]) {
                    space = baskets[j];
                    spaceCnt.put(space, spaceCnt.getOrDefault(baskets[j], 0) + 1);
                    j++;
                }
                if (j == n)
                    ans++;
                else
                    j++;
            } else { // 左边能找到
                int remain = left.getValue();
                if (remain == 1) {
                    spaceCnt.remove(left.getKey());
                }
            }
            i++;
        }
        return ans;
    }

    public static void main(String[] args) {
        final FruitsIntoBaskets fruitIntoBask = new FruitsIntoBaskets();
        int[] fruits = new int[] {3, 6, 1};
        int[] baskets = new int[] {6, 4, 7};
        final int i = fruitIntoBask.numOfUnplacedFruits(fruits, baskets);
        System.out.println(i);
    }
}
