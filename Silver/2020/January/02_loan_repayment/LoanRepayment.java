import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class LoanRepayment {
    static long N, K, M;
    static long ans = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("loan.in"));
        PrintWriter pw = new PrintWriter("loan.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        N = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        // 二分查找最大的 x
        long low = 1;
        long high = Long.MAX_VALUE / 2;
        while (low < high) {
            long mid = (low + high + 1) / 2;
            if (check(mid)) {
                low = mid;
                ans = mid;
            } else {
                high = mid - 1;
            }
        }
        pw.println(ans);
        pw.close();
    }

    /**
     * 检查当前的 X 能否在 K 天后给出至少 N 加仑牛奶
     */
    static boolean check(long x) {
        long remainDays = K;
        // 已经给了 g 加仑
        long g = 0;
        while (remainDays > 0 && g < N) {
            // 每次给出的 y 会随着时间慢慢减少
            long y = (N - g) / x;
            if (y < M) {
                // 当 y < M 时，之后的每天都是给 M
                // 直接计算剩余的天数是否足够
                long leftover = ((N - g) + (M - 1)) / M;
                return leftover <= remainDays;
            }
            // 可能连续多天给的 y 都是相同的
            long maxMatch = N - (x * y);
            long numDays = Math.min((maxMatch - g) / y + 1, remainDays);
            g += y * numDays;
            remainDays -= numDays;
        }
        return g >= N;
    }
}