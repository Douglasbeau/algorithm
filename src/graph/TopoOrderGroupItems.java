package graph;

import java.util.*;

public class TopoOrderGroupItems {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        Node[] nodes = new Node[n];
        int[] groupSizes = new int[m];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i, group[i]);
            if (group[i] != -1) {
                groupSizes[group[i]]++;
            }
        }
        Group[] groups = new Group[m];
        for (int i = 0; i < m; i++) {
            groups[i] = new Group(i);
        }
        // 建立拓扑关系
        for (int i = 0; i < beforeItems.size(); i++) {
            Node node = nodes[i];
            Group curGroup = null;
            if (node.group != -1) {
                curGroup = groups[node.group];
            }
            for (int b : beforeItems.get(i)) {
                Node before = nodes[b];
                before.addNext(node);
                if (curGroup != null && before.group != -1)
                    groups[before.group].addNext(curGroup);
            }
        }
        // 输出的组 i+1是i组的成员
        int[][] groupMembers = new int[m][];
        int[] gp = new int[m];
        int groupCntWithItems = 0;
        for (int i = 0; i < m; i++) {
            groupMembers[i] = new int[groupSizes[i]];
            groupCntWithItems++;
        }
        Node[] zeroInQ = new Node[n];
        int l = 0;
        int r = 0;
        List<Integer> noPreNoGroup = new ArrayList<>();
        // 选择in=0的
        for (int i = 0; i < n; i++) {
            if (nodes[i].in == 0) {
                zeroInQ[r++] = nodes[i];
                int grp = nodes[i].group;
                if (grp == -1)
                    noPreNoGroup.add(nodes[i].val);
                else {
                    groupMembers[grp][gp[grp]++] = nodes[i].val;
                }
            }
        }
        List[] afters = new ArrayList[m]; // grp后的无组成员
        List<Integer> relyOnMinusOne = new ArrayList<>();

        // 出队
        while(l < r) {
            Node cur = zeroInQ[l++];
            if (cur.nexts == null)
                continue;
            for (Node node : cur.nexts) {
                node.in--;
                if(node.in == 0) {
                    zeroInQ[r++] = node;
                    int grp = node.group;
                    if (grp == -1 && cur.group != -1){
                        if (afters[cur.group] == null) {
                            afters[cur.group] = new ArrayList();
                        }
                        afters[cur.group].add(node.val);
                    }
                    else if (grp != -1) {
                        groupMembers[grp][gp[grp]++] = node.val;
                    } else { // 都-1
                        relyOnMinusOne.add(node.val);
                    }
                }
            }
        }
        if (r < n)
            return new int[]{};

        int[] ans = new int[n];
        int j = 0;
        // 按组输出
        for (int a : noPreNoGroup) {
            ans[j++] = a;
        }
        Group[] zeroInGroupQ = new Group[m];
        r = 0;
        l = 0;
        int[] groupOrder = new int[m];
        int gi = 0;
        for (int i = 0; i < m; i++) {
            Group curGroup = groups[i];
            if (curGroup.in == 0) {
                zeroInGroupQ[r++] = curGroup;
                groupOrder[gi++] = curGroup.val;
            }
        }
        while (l < r) {
            Group curGroup = zeroInGroupQ[l++];
            if (curGroup.nexts != null) {
                for (Group nextGroup : curGroup.nexts) {
                    nextGroup.in--;
                    if (nextGroup.in == 0) {
                        zeroInGroupQ[r++] = nextGroup;
                        groupOrder[gi++] = nextGroup.val;
                    }
                }
            }
        }
        if (r < groupCntWithItems)
            return new int[]{};

        for (int i = 0; i < m; i++) {
            int grp = groupOrder[i];
            for (int mem : groupMembers[grp]) {
                ans[j++] = mem;
            }
            if (afters[grp] == null)
                continue;
            for (int k = 0; k < afters[grp].size(); k++) {
                int val = (int)afters[grp].get(k);
                ans[j++] = val;
            }
        }
        for (int i : relyOnMinusOne) {
            ans[j++] = i;
        }
        return ans;
    }

    private static class Node {
        int val;
        int in;
        int group;
        List<Node> nexts;
        Node(int val, int group) {
            this.val = val;
            this.group = group;
        }
        void addNext(Node n) {
            if (nexts == null)
                nexts = new ArrayList<>();
            nexts.add(n);
            n.in++;
        }
    }
    private static class Group {
        int val;
        int in;
        Set<Group> nexts;
        Group(int val) {
            this.val = val;
        }
        void addNext(Group g) {
            if (g.val == this.val)
                return;
            // if (g.nexts != null && g.nexts.contains(this)) {
            //     return;
            // }
            if (nexts == null) {
                nexts = new HashSet<>();
            }
            if (nexts.contains(g))
                return;
            nexts.add(g);
            g.in++;
        }
    }

    public static void main(String[] args) {
        final TopoOrderGroupItems topoOrderGroupItems = new TopoOrderGroupItems();
//        List<List<Integer>> bis = new ArrayList<>();
//        bis.add(Collections.EMPTY_LIST);
//        bis.add(Collections.singletonList(6));
//        bis.add(Collections.singletonList(5));
//        bis.add(Collections.singletonList(6));
//        bis.add(Arrays.asList(3, 6));
//        bis.add(Collections.EMPTY_LIST);
//        bis.add(Collections.EMPTY_LIST);
//        bis.add(Collections.EMPTY_LIST);
//        int[] ints = topoOrderGroupItems.sortItems(8, 2, new int[]{-1, -1, 1, 0, 0, 1, 0, -1}, bis);
//        System.out.println(Arrays.toString(ints));

        List<List<Integer>> bis = new ArrayList<>();
        bis.add(Collections.singletonList(3));
        bis.add(Arrays.asList(6, 0));
        bis.add(Collections.singletonList(5));
        bis.add(Collections.singletonList(6));
        bis.add(Arrays.asList(3, 6, 7));
        bis.add(Collections.EMPTY_LIST);
        bis.add(Collections.EMPTY_LIST);
        bis.add(Collections.EMPTY_LIST);
        int[] ints = topoOrderGroupItems.sortItems(8, 2, new int[] {-1,-1,1,0,0,1,0,-1}, bis);
        System.out.println(Arrays.toString(ints));
    }
}
