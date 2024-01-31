import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class WhyDidTheCowCrossTheRoadII {
    static int N, K, B;
    static int[] signals;
    static int[] brokenPrefixSum;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("maxcross.in"));
        PrintWriter pw = new PrintWriter("maxcross.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        signals = new int[N];
        brokenPrefixSum = new int[N + 1];
        for (int i = 0; i < B; i++) {
            signals[Integer.parseInt(r.readLine()) - 1]++;
        }
        for (int i = 0; i < N; i++) {
            brokenPrefixSum[i + 1] = signals[i] + brokenPrefixSum[i];
        }
        // 遍历 K 长度范围内坏的信号灯数量
        int ret = N + 1;
        for (int i = 0; i <= N - K; i++) {
            ret = Math.min(ret, brokenPrefixSum[i + K] - brokenPrefixSum[i]);
        }
        pw.println(ret);
        pw.close();
    }
}