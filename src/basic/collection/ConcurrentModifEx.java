package basic.collection;

import java.util.*;

public class ConcurrentModifEx {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(2);
        // 二、使用下面方法会漏掉
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 1)
                list.remove(i);
        }
        System.out.println(list);
        // 一、使用iterator可以正确移除
        final Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 1)
                iterator.remove();
        }
        System.out.println(list);

        list.set(0, 1);
        // 三、增强的for循环会抛异常
        for (Integer value : list) {
            if (value == 1) {
                list.remove(value);
            }
        }

        // 遍历entrySet时删除，也会导致异常，modCount维护被修改的状态
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        map.put(3, 6);
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() == 2)
                map.remove(entry.getKey());
        }
        System.out.println(map);
    }
}
