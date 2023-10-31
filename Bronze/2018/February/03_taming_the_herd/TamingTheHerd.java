import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class TamingTheHerd {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("taming.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("taming.out"));
        int n = Integer.parseInt(r.readLine());
        StringTokenizer st = new StringTokenizer(r.readLine());
        int[] logs = new int[n];
        for (int i = 0; i < n; i++) {
            logs[i] = Integer.parseInt(st.nextToken());
        }
        // 从后往前用已有信息填充 -1 处 如
        // -1 -1 0 -1 -1 3 -1 -1 -1 -1 2
        // -1 -1 0  1  2 3 -1 -1  0  1 2
        // 其中的 2 和 3 可以确定前面几位的数字
        int logDays = 0;
        boolean flag = false;
        for (int i = n - 1; i >= 0; i--) {
            if (logs[i] != -1) {
                flag = true;
                logDays = logs[i];
            } else {
                if (flag && logDays > 0) {
                    logDays--;
                    logs[i] = logDays;
                    if (logDays == 0) {
                        flag = false;
                    }
                }
            }
        }
        // 判断记录是否合法  如 1 2 3 5 序列为不合法
        boolean valid = true;
        for (int i = 1; i < n; i++) {
            if (logs[i - 1] == 0 && logs[i] > 1) {
                valid = false;
                break;
            }
            if (logs[i] > 0 && logs[i - 1] > 0 && logs[i] != logs[i - 1] + 1) {
                valid = false;
                break;
            }
        }
        if (!valid) {
            pw.println(-1);
        } else {
            // 找到最大最小值
            // 最大值时可以将所有的 -1 都写为 0
            // 最小值时只考虑前缀的 -1 如
            // -1 -1 -1 0 1 2 -1 0 1 -1
            //  0  1  2 0 1 2  3 0 1  2
            int maxDays = 0, minDays = 0;
            for (int i = 0; i < n; i++) {
                if (logs[i] == 0) {
                    maxDays++;
                    minDays++;
                }
                if (logs[i] == -1) {
                    maxDays++;
                }
            }
            if (logs[0] == -1) {
                minDays++;
            }
            pw.println(minDays + " " + maxDays);
        }
        pw.close();
    }
}
