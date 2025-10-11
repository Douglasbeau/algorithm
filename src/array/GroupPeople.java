package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 给定每人想在group的大小，输出组对结果
public class GroupPeople {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        // traverse the group size arr, and put person to the group of the size desired
        Map<Integer, List<Integer>> sizeToGroup = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();
        for(int i=0; i<groupSizes.length; i++) {
            int size = groupSizes[i];
            List<Integer> curGroup = sizeToGroup.computeIfAbsent(size, k -> new ArrayList<>());
            // add to the group in forming
            curGroup.add(i);
//            System.out.printf("forming group of size: %d, desired group size: %d%n", size, curGroup.size());

            // if group formed, set null
            if(curGroup.size() == size) {
//                System.out.println("formed group of size: " + size);
                result.add(curGroup);
                sizeToGroup.put(size, null);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        GroupPeople groupPeople = new GroupPeople();
        int[] a = new int[]{3,2,2,3,3,3,3,3};
        List<List<Integer>> lists = groupPeople.groupThePeople(a);
        lists.forEach(System.out::println);
    }
}
