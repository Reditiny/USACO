import java.io.*;
import java.util.*;

public class SleepingInClass {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int t = Integer.parseInt(r.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(r.readLine());
            int[] elsieLog = new int[n];
            int logSum = 0;
            StringTokenizer st = new StringTokenizer(r.readLine());
            for (int j = 0; j < n; j++) {
                elsieLog[j] = Integer.parseInt(st.nextToken());
                logSum += elsieLog[j];
            }
            // 遍历所有可能的时间数
            for (int numHours = 0; numHours <= logSum; numHours++) {
                if (numHours != 0 && logSum % numHours != 0) {
                    continue;
                }
                int curSum = 0;
                boolean valid = true;
                // 依次合并时间 如果合并后的时间大于 numHours 说明仅通过合并不能满足条件
                for (int h : elsieLog) {
                    curSum += h;
                    if (curSum > numHours) {
                        valid = false;
                        break;
                    } else if (curSum == numHours) {
                        curSum = 0;
                    }
                }
                if (valid) {
                    if (numHours == 0) {
                        pw.println(0);
                    } else {
                        pw.println(n - logSum / numHours);
                    }
                    break;
                }
            }
        }
    }
}