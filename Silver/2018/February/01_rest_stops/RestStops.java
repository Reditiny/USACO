import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class RestStops {
    static int L, N, rf, rb;
    static int[] x, c;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("reststops.in"));
        PrintWriter pw = new PrintWriter("reststops.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        rf = Integer.parseInt(st.nextToken());
        rb = Integer.parseInt(st.nextToken());
        x = new int[N];
        c = new int[N];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(r.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
        }

        // 设A休息站和后面的休息站B，如果 B>A 那么 Bessie 不会在 A 停留
        // 因此 Bessie 只会在那些后面的休息站都更差的休息站停留
        boolean[] good = new boolean[N];
        int maxTastiness = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (c[i] > maxTastiness) {
                good[i] = true;
                maxTastiness = c[i];
            }
        }
        // 从前往后遍历，如果当前休息站是好的，那么就停留
        int prevStopPos = 0;
        long ans = 0;
        for (int i = 0; i < N; i++) {
            if (good[i]) {
                long travelDist = x[i] - prevStopPos;
                long fTime = travelDist * rf;
                long bTime = travelDist * rb;
                long restTime = fTime - bTime;
                ans += restTime * c[i];
                prevStopPos = x[i];
            }
        }
        pw.println(ans);
        pw.close();
    }
}