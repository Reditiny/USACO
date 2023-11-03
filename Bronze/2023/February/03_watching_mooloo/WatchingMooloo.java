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
        // 对于任意一天订阅有两种方式: 1.新订阅 2.上次订阅延续到今天
        BigInteger ans = BigInteger.valueOf(1 + k);
        BigInteger lastDay = watchDays.get(0);
        for (int i = 1; i < watchDays.size(); i++) {
            BigInteger curDay = watchDays.get(i);
            // 新订阅花 1+k 上次订阅到今天花 curDay - lastDay
            // 选择 curDay - lastDay 和  k + 1 之中较小的方案
            // BigInteger的运算并不会改变本身的值而是返回一个新的BigInteger，它值等于运算结果
            ans = ans.add(BigInteger.valueOf(k+1).min(curDay.subtract(lastDay)));
            lastDay = curDay;
        }
        pw.println(ans);
        pw.close();
    }
}
