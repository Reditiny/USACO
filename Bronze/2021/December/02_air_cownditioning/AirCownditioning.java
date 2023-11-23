import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class AirCownditioning {
    static int N;
    static int[] temperatureDiff;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        N = Integer.parseInt(r.readLine());
        temperatureDiff = new int[N];
        StringTokenizer stP = new StringTokenizer(r.readLine());
        StringTokenizer stT = new StringTokenizer(r.readLine());
        for (int i = 0; i < N; i++) {
            temperatureDiff[i] = Integer.parseInt(stP.nextToken()) - Integer.parseInt(stT.nextToken());
        }
        for (int i = 0; i < N; i++) {
            if (temperatureDiff[i] == 0) {
                continue;
            }
            int direction = 1;
            if (temperatureDiff[i] < 0) {
                direction = -1;
            }
            // 每次调整温度的时候，尽可能多的调整房间，调整量为这些房间中温度差的最小值
            int diff = Math.abs(temperatureDiff[i]);
            while (diff > 0) {
                for (int j = i; j < N; j++) {
                    if (temperatureDiff[j] * direction <= 0) {
                        break;
                    }
                    diff = Math.min(diff, Math.abs(temperatureDiff[j]));
                }
                for (int j = i; j < N; j++) {
                    if (temperatureDiff[j] * direction <= 0) {
                        break;
                    }
                    temperatureDiff[j] -= direction * diff;
                }
                ans += diff;
                diff = Math.abs(temperatureDiff[i]);
            }
        }
        pw.println(ans);
        pw.close();
    }
}