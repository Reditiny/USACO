import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class Drought {
    static int t;
    static int n;
    // 因为有些case数值太大所以使用BigInteger防止溢出
    static BigInteger[] h;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        t = Integer.parseInt(r.readLine());
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(r.readLine());
            h = new BigInteger[n];
            StringTokenizer st = new StringTokenizer(r.readLine());
            for (int j = 0; j < n; j++) {
                h[j] = BigInteger.valueOf(Long.parseLong(st.nextToken()));
            }
            pw.println(feedCow());
        }
        pw.close();
    }

    private static BigInteger feedCow() {
        BigInteger count = BigInteger.valueOf(0);
        for (int i = 0; i < n - 1; i++) {
            if (h[i + 1].compareTo(h[i]) > 0) {
                // 如果 i+1 比 i 大，那么就必须用 i+2 来配合 i+1 减小
                // 如果 i+2 不存在或者 i+2 不够大，那么就无法完成
                if (i + 2 >= n) {
                    return BigInteger.valueOf(-1);
                }
                BigInteger difference = h[i + 1].subtract(h[i]);
                h[i + 1] = h[i + 1].subtract(difference);
                h[i + 2] = h[i + 2].subtract(difference);
                if (h[i + 2].compareTo(BigInteger.valueOf(0)) < 0) {
                    return BigInteger.valueOf(-1);
                }
                count = count.add(difference.multiply(BigInteger.valueOf(2)));
            } else if (h[i + 1].compareTo(h[i]) < 0) {
                // 如果 i 比 i+1 大，由于从前往后遍历，所以 0-i 的数值都已经一样了
                // 这个时候要两个两个的减小，0 1一起减，2 3一起减，4 5一起减....一直到 i-1 i
                // 所以 i 必须是奇数否则无法完成
                if (i % 2 == 0) {
                    return BigInteger.valueOf(-1);
                }
                BigInteger difference = h[i].subtract(h[i + 1]);
                count = count.add(difference.multiply(BigInteger.valueOf(i + 1)));
            }
        }
        return count;
    }
}