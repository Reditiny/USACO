import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class SearchingForSoulmates {
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        n = Integer.parseInt(r.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            pw.println(countChangeOperation(a, b));
        }
        pw.close();
    }

    /**
     * 计算从 a 到 b 的变换次数
     */
    static long countChangeOperation(long a, long b) {
        if (a == b) {
            return 0;
        } else if (a > b) {
            // a > b 时只能通过减小 a ，即 /2 来使两者靠近
            // a 除 2 并向上取整
            long remain = a % 2;
            return 1 + remain + countChangeOperation((a + remain) / 2, b);
        } else {
            // a < b 时可以通过增大 a ，即 *2 或 +1 来使两者靠近
            // 减小 b 和增大 a 的效果是一样的
            // 因为 a > b 时 a 除 2，现在再乘 2 就会进入死循环
            // 所以 b 除 2 并向下取整
            long remain = b % 2;
            return Math.min(b - a, 1 + remain + countChangeOperation(a, b / 2));
        }
    }
}