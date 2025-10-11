package map;

import java.util.*;

public class TaskManager {
    TreeSet<TaskInfo> set;
    Map<Integer, TaskInfo> map;

    public TaskManager(List<List<Integer>> tasks) {
        set = new TreeSet<>((a, b) -> a.p.equals(b.p) ? b.tid-a.tid : b.p - a.p);
        map = new HashMap<>();
        for (List<Integer> task : tasks) {
            TaskInfo ti = new TaskInfo(task.get(1), task.get(2), task.get(0));
            map.put(task.get(1), ti);
            set.add(ti);
        }
    }

    public void add(int userId, int taskId, int priority) {
        TaskInfo ti = new TaskInfo(taskId, priority, userId);
        map.put(taskId, ti);
        set.add(ti);
    }

    public void edit(int taskId, int newPriority) {
        TaskInfo ti = map.get(taskId);
        if (ti.p.equals(newPriority))
            return;
        set.remove(ti);
        ti.p = newPriority;
        set.add(ti);
    }

    public void rmv(int taskId) {
        TaskInfo ti = map.remove(taskId);
        set.remove(ti);
    }

    public int execTop() {
        if (set.isEmpty())
            return -1;
        TaskInfo ti = set.pollFirst();
        map.remove(ti.tid);
        return ti.uid;
    }
    static class TaskInfo {
        Integer tid;
        Integer p;
        Integer uid;
        TaskInfo(Integer tid, Integer p, Integer uid) {
            this.tid = tid;
            this.p = p;
            this.uid = uid;
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> tasks = new ArrayList<>();
        tasks.add(Arrays.asList(5, 100, 8));
        tasks.add(Arrays.asList(5, 101, 7));
        tasks.add(Arrays.asList(6, 102, 8));
        tasks.add(Arrays.asList(7, 103, 9)); //5
        final TaskManager taskManager = new TaskManager(tasks);
        taskManager.edit(103, 5);
        taskManager.add(5, 104, 5);
        taskManager.edit(104, 6);
        final int i = taskManager.execTop();
        System.out.println(i);
    }
}