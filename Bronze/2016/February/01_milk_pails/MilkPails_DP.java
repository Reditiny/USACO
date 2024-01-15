import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class MilkPails_DP {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("pails.in"));
        PrintWriter pw = new PrintWriter("pails.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        // dp[i] 表示是否可以得到 i
        // 动态转移方程为 dp[i] = dp[i - x] || dp[i - y]
        boolean[] dp = new boolean[m + 1];
        dp[0] = true;

        for (int i = 0; i <= m; i++) {
            if (!dp[i]) {
                continue;
            }

            if (i + x <= m) {
                dp[i + x] = true;
            }

            if (i + y <= m) {
                dp[i + y] = true;
            }
        }

        for (int i = m; i >= 0; i--) {
            if (dp[i]) {
                pw.println(i);
                break;
            }
        }

        pw.close();
    }
}
