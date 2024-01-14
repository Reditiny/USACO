import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class AirCownditioning2 {
    static int N;
    static int[] temperatureDiff;
    static int[] deltaDiff;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        N = Integer.parseInt(r.readLine());
        temperatureDiff = new int[N + 2];
        deltaDiff = new int[N + 1];
        StringTokenizer stP = new StringTokenizer(r.readLine());
        StringTokenizer stT = new StringTokenizer(r.readLine());
        for (int i = 0; i < N; i++) {
            temperatureDiff[i + 1] = Integer.parseInt(stP.nextToken()) - Integer.parseInt(stT.nextToken());
        }
        // 为了方便处理，将温度差数组的首尾都设置为 0
        for (int i = 1; i < temperatureDiff.length; i++) {
            deltaDiff[i - 1] = Math.abs(temperatureDiff[i] - temperatureDiff[i - 1]);
        }
        // 目标是使每个 deltaDiff[i] 都为 0
        int sum = 0;
        for (int value : deltaDiff) {
            sum += value;
        }
        // 当从 temperatureDiff[i] 增加/减少到 temperatureDiff[j] 时
        // deltaDiff[i - 1] 和 deltaDiff[j + 1] 上都有变化
        pw.println(sum / 2);
        pw.close();
    }
}