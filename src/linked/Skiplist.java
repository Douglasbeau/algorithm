package linked;

// 跳表，实现其 查找、增加、删除
public class Skiplist {
    private static final int MAX_LEVEL = 16;
    private final SkipNode root = new SkipNode(-1, MAX_LEVEL);
    //private final SkipNode nil = new SkipNode(-1, MAX_LEVEL);
    public Skiplist() {
    }

    public boolean search(int target) {
        SkipNode cur = root;
        int level = cur.nexts.length;
        SkipNode next = cur.nexts[--level];
        while (target != cur.val) {

            while(target < next.val) {
                next = next.nexts[level];
            }
        }
        return true;
    }

    public void add(int num) {
        // 从root开始，遍历nexts，遇到 小于自己的就跳，大于自己的就找其低level的节点
        SkipNode cur = root;
        SkipNode next = null;
        // 优先遍历level，其次遍历
        int level = MAX_LEVEL;
        while (level > 0) {
            next = cur.nexts[--level];
            if (next == null) {
                continue;
            }
            // 相等 不用add
            if (next.val == num) {
                return;
            }
            // 只要next较num小，就跳它
            while (next.val < num) {
                cur = next;
                level = cur.nexts.length;
                next = cur.nexts[--level];
            }
            // 遇到 较num大的，level还要往下

        }
        // level此处为0
        int randomLevel = 0;//randLevel();
        SkipNode newNode = new SkipNode(num, randomLevel);
        //TODO 如何挡住前面所有不higher的
        //...
        // 指向后面
        if (next != null) {
            int nextLevel = next.nexts.length;
            int i = 0;
            while (i < randomLevel) {
                while (i < nextLevel) {
                     newNode.nexts[i] = next;
                     i++;
                }
                next = next.nexts[nextLevel - 1];
                nextLevel = next.nexts.length;
            }
        }


    }

    public boolean erase(int num) {
        return false;
    }
}

//
class SkipNode{
    int val;
    SkipNode[] nexts;
    SkipNode(int val, int level) {
        this.val = val;
        nexts = new SkipNode[level];
    }
}