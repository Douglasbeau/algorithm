package backtrace;

import java.util.*;

// 全排列
public class Permutation {
    Map<String, List<String>> map = new HashMap<>(); // cache
    // 每个字符都只出现一次
    public List<String> permute(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        if (map.containsKey(s)) {
            return map.get(s);
        }

        for (int i = 0; i < s.length(); i++) {
            char chose = s.charAt(i);
            String remain = s.substring(0, i) + (i == s.length() - 1 ? "" : s.substring(i + 1));
            if (remain.length() == 1) {
                ans.add(chose + remain);
            } else {
                List<String> subList = permute(remain);
                for (String sub : subList) {
                    ans.add(chose + sub);
                }
            }
        }
        map.put(s, ans);
        return ans;
    }
    // 考虑重复字符
    public List<String> permute2(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }

        if (map.containsKey(s)) {
            return map.get(s);
        }
        Set<Character> choices = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char chose = s.charAt(i);
            if (choices.contains(chose)) {
                continue;
            }
            choices.add(chose);
            String remain = s.substring(0, i) + (i == s.length() - 1 ? "" : s.substring(i + 1));
            if (remain.length() == 1) {
                ans.add(chose + remain);
            } else {
                List<String> subList = permute2(remain);
                for (String sub : subList) {
                    ans.add(chose + sub);
                }
            }
        }
        map.put(s, ans);
        return ans;
    }

    public static void main(String[] args) {
        Permutation permutation = new Permutation();
        long start = System.currentTimeMillis();
//        List<String> result = permutation.permute2("aacc");
        List<List<Integer>> result = permutation.permute(new int[]{1, 3, -10});
        System.out.println(result);
        System.out.println("time cost: " + (System.currentTimeMillis() - start) +
                " size: " + result.size() +
                " map size: " + permutation.map.size());
    }

    Map<String, List<List<Integer>>> cache = new HashMap<>();

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        String key = getKey(nums);
        if(cache.containsKey(key)) {
            return cache.get(key);
        }

        if(nums.length == 1) {
            ans.add(Collections.singletonList(nums[0]));
            return ans;
        }

        for (int i=0; i<nums.length; i++) {
            Integer select = nums[i];
            int[] left = new int[nums.length - 1];
            // 添加除i外的所有元素到left
            for (int m=0, n=0; n<nums.length;) {
                if (n != i) {
                    left[m++] = nums[n++];
                } else {
                    n++;
                }
            }

            List<List<Integer>> sub = permute(left);
            for (List<Integer> list : sub) {
                ArrayList<Integer> newList = new ArrayList<>(nums.length);
                newList.add(select);
                newList.addAll(list);
                ans.add(newList);
            }
        }

        cache.put(key, ans);
        return ans;
    }

    private String getKey(int[] nums) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<nums.length-1; i++) {
            sb.append(nums[i]).append(",");
        }
        sb.append(nums[nums.length - 1]);
        return sb.toString();
    }
}
