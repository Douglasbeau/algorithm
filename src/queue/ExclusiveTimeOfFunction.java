package queue;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

// 由线程启动、停止时间，返回每个线程执行的时长
//["0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"]
public class ExclusiveTimeOfFunction {
    public int[] exclusiveTime(int n, List<String> logs) {
        Stack<Integer> stack = new Stack<>();
        int[] time = new int[n];
        int preTimeStamp = 0;
        int timestamp;
        int fid;
        boolean isStart = true;
        // 遍历每一条log，判断是start 还是end
        String[] logInfo;
        for (String log : logs) {
            logInfo = log.split(":");
            fid = Integer.parseInt(logInfo[0]);
            timestamp = Integer.parseInt(logInfo[2]);
            // start，该function id开始，中止上一个
            if("start".equals(logInfo[1])) {
                // 上一个计时中止, 压栈
                if(!stack.empty()) {
                    int delta = timestamp - preTimeStamp + (isStart ? 0 : -1);
                    time[stack.peek()] += delta;
                }
                // 当前fid计时开始
                stack.push(fid);
                isStart = true;
            }
            // end -> pop function id, start the next one
            else {
                fid = stack.pop();
                int delta = timestamp - preTimeStamp + (isStart ? 1 : 0);
                time[fid] += delta;

                isStart = false;
            }
            preTimeStamp = timestamp;

        }
        return time;
    }

    public static void main(String[] args) {
        List<String> logs = Arrays.asList("0:start:0","0:start:2","0:end:5","1:start:7","1:end:7","0:end:8");
        ExclusiveTimeOfFunction et = new ExclusiveTimeOfFunction();
        int[] time = et.exclusiveTime(2, logs);
        System.out.println(Arrays.toString(time));
    }
}
