package bag;
// 一个背包能装最大重量是W，有若干物品，物品的重量、价值分别为int[]w和int[] v，求背包能装的物品累积的最大价值
// 进阶问题： 并返回相应的物品组合 -> 增加一个参数 Set类型的 picked
// 怎么记录选取的物品才准确???
import java.util.*;

public class BagSolution {
    public static void main(String[] args) {
        int[][] input = readFromStdin();
        int[] w = input[0];
        int[] v = input[1];
        int DEAD_WEIGHT = input[2][0];
//        int DEAD_WEIGHT = 10;
//        int[] w = {2,3,4,5};
//        int[] v = {3,5,7,8};
        int[][] cache = new int[w.length+1][DEAD_WEIGHT +1];
        for (int[] row : cache) {
            Arrays.fill(row, -1);
        }
        Arrays.fill(cache[w.length], 0);
        Set<Integer> picked = new LinkedHashSet<>();
        int result = calcMaxValue(DEAD_WEIGHT, w, v, 0, cache, picked);
        System.out.println("max value is : " + result);
//        System.out.println("picked indices : " + picked);
    }

    public static int calcMaxValue(int remainWeight, int[] w, int[] v, int index, int[][]cache, Set<Integer> picked) {
//        // 已经遍历了所有
//        if (index >= w.length) {
//            return 0;
//        }
        // 可以记录一下remainWeight,index对应的返回值，如果不是-1就直接返回
        if (cache[index][remainWeight] != -1) {
//            System.out.printf("calculated value for index %d and remaining capacity %d %n", index, remainWeight);
            return cache[index][remainWeight];
        }
        int result;
        // 剩余载重不够装
        if (remainWeight < w[index]) {
            result = calcMaxValue(remainWeight, w, v, index + 1, cache, picked);
            cache[index][remainWeight] = result;
            return result;
        }

        // 遍历到第index个，装或不装进背包，取价值最大的
        int valueIfPick = v[index] + calcMaxValue(remainWeight - w[index], w, v, index+1, cache, picked);
        int valueLeave = calcMaxValue(remainWeight, w, v, index+1, cache, picked);
        if (valueIfPick >= valueLeave) {
            result = valueIfPick;
//            picked.add(index);
        } else {
//            picked.remove(index);
            result =valueLeave;
            
        }
        cache[index][remainWeight] = result;
        return result;
    }

    private static int[][] readFromStdin() {
        int[][] input = new int[3][];
        Scanner scanner = new Scanner(System.in);
        System.out.print("weights: ");
        String line = scanner.next();
        String[] split = line.split(",");
        int[] weights = new int[split.length];
        for (int i=0; i< weights.length; i++) {
            weights[i] = Integer.parseInt(split[i]);
        }

        System.out.print("values: ");
        line = scanner.next();
        split = line.split(",");
        int[] values = new int[split.length];
        for (int i=0; i< values.length; i++) {
            values[i] = Integer.parseInt(split[i]);
        }
        System.out.print("bag capacity (DEAD_WEIGHT): ");
        int max = scanner.nextInt();
        input[0] = weights;
        input[1] = values;
        input[2] = new int[]{max};
        return input;
    }
}
