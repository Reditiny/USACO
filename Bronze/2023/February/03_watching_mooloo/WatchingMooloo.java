import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class WatchingMooloo {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        // 由于数据较大均使用BigInteger操作
        List<BigInteger> watchDays = new ArrayList<>();
        st = new StringTokenizer(r.readLine());
        for (int i = 0; i < n; i++) {
            watchDays.add(new BigInteger(st.nextToken()));
        }
        watchDays.sort(BigInteger::compareTo);
        // 对于任意一天订阅有两种方式: 1.新订阅 2.上次订阅延续到今天
        BigInteger ans = BigInteger.valueOf(1 + k);
        BigInteger lastDay = watchDays.get(0);
        for (int i = 1; i < watchDays.size(); i++) {
            BigInteger curDay = watchDays.get(i);
            // 新订阅花 1+k 上次订阅到今天花 curDay - lastDay
            // curDay - lastDay <= k 时应该选择方案2否则方案1
            if (curDay.subtract(lastDay).compareTo(BigInteger.valueOf(k)) <= 0) {
                ans = ans.add(curDay.subtract(lastDay));
            } else {
                ans = ans.add(BigInteger.valueOf(k + 1));
            }
            lastDay = curDay;
        }
        pw.println(ans);
        pw.close();
    }
}
