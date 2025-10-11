package basic;

import java.util.*;
import java.util.stream.Collectors;

// 输入一个字符串数组，请输出忽略大小写不重复
// 重点是熟悉使用lambda实现
public class ReadStrings {
    static String[] ss = {"I", "PICKED", "fifty", "yuan", "and", "Picked", "vene", "Wombat", "wombat"};

    public static void main(String[] args) {
//        getResult(ss);

        //使用lambda
        String collect = Arrays.stream(ss).filter(s -> s.length() > 4)
                .map(String::toLowerCase)
                .distinct()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining("❤️"));
        System.out.println(collect);
    }

    public static String[] getResult(String [] ss) {
        HashSet<String> set = new HashSet<>();
        ArrayList<String> processed = new ArrayList<>();
        for(String s : ss) {
            if(s.length() < 5){
                continue;
            }
            String s1 = s.toLowerCase();
            if (!set.contains(s1)){
                set.add(s1);
//                System.out.println("added " + s);
                processed.add(s);
            }
        }
        processed.sort(Comparator.comparing(String::toLowerCase));
        for (String s : processed)
            System.out.print(s+ ' ');
        System.out.println();
        return processed.toArray(new String[0]);
    }
}
