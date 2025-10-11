package linked;

// DONE 链表按与n的大小关系分3个区： < = >
// 法一：用数组 -- 笔试
// 法二：不需要容器，记录每个区头尾 -- 面试
public class ThreeParts {
    public static void main(String[] args) {
        Node list = Node.randLinkedList(8);
        System.out.println("before partition: ");
        System.out.println(list);

//        Node newList = partition1(list, 8);
        Node newList = partition2(list, 8);

        System.out.println("after partition: ");
        System.out.println(newList);
    }

    public static Node partition1(Node head, int C) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        int cnt = 0;
        while (cur != null) {
            cnt++;
            cur = cur.next;
        }
        cur = head;
        // 链表转数组
        Node[] arr = new Node[cnt];
        for (int i = 0; i < cnt; i++) {
            arr[i] = cur;
            cur = cur.next;
        }
        // 操作数组，进行分区
        partitionArray(arr, C);
        // 数组转链表
        int i = 0;
        for(; i< arr.length-1; i++) {
            arr[i].next = arr[i+1];
        }
        arr[i].next = null;
        return arr[0];
    }

    // 直接操作链表
    public static Node partition2(Node head, int C) {
        // 三个区的首位节点初始化
        Node lH = null;
        Node lT = null;
        Node eH = null;
        Node eT = null;
        Node gH = null;
        Node gT = null;
        Node cur = head;
        while (cur!=null) {
            if (cur.value < C) {
                if (lH == null) {
                    lH = cur;
                } else {
                    lT.next = cur;
                }
                lT = cur;
            } else if (cur.value == C) {
                if (eH == null) {
                    eH = cur;
                } else {
                    eT.next = cur;
                }
                eT = cur;
            } else {
                if (gH == null) {
                    gH = cur;
                } else {
                    gT.next = cur;
                }
                gT = cur;
            }
            cur = cur.next;
        }
        if (lH != null)
            lT.next = null;
        if (eH != null)
            eT.next = null;
        if (gH != null)
            gT.next = null;

        // 串联起来
        // 1. 有l区
        if (lH != null) {
            if (eH != null) {
                lT.next = eH;
                eT.next = gH;
            } else {
                lT.next = gH;
            }
            return lH;
        }
        // 2. 没有l区，有e区
        if (eH != null) {
            eT.next = gH;
            return eH;
        }
        return gH;

    }

    private static void partitionArray(Node[] list, int C) {
        // 将数组分区，小于则放左 边界l，等于放右 边界r，
        int l = -1;
        int r = list.length;
        for (int i=0; i< list.length && i != r; ) {
            if (list[i].value < C) {
                swap(list, ++l, i++);
            } else if (list[i].value == C) {
                i++;
            } else {
                // 大于，r往左走
                swap(list, i, --r);
            }
        }
    }

    private static void swap(Node[] list, int i, int j) {
        if (i == j)
            return;
        Node tmp = list[i];
        list[i] = list[j];
        list[j] = tmp;
    }
}
