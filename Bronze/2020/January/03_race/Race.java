import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class Race {
    static int k;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("race.in"));
        PrintWriter pw = new PrintWriter("race.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(r.readLine());
            pw.println(run(x));
        }
        pw.close();
    }

    /**
     * 计算最大速度 maxSpeed 时花费最少时间
     * 最优策略开始应该尽可能的加速，最后再减速至 maxSpeed
     *         ----
     *       -     - maxSpeed
     *     -
     *   - 0         速度变化趋势
     */
    static int run(int maxSpeed) {
        // 分别计算加速过程和减速过程的距离
        int speedUpDist = 0;
        int slowDownDist = 0;
        int time = 0;
        // 一直加速，同时计算加速与减速的距离，直到总距离超过 k
        for (int currSpeed = 1; ; currSpeed++) {
            speedUpDist += currSpeed;
            time++;
            if (speedUpDist + slowDownDist >= k) {
                return time;
            }
            // 当前速度超过 maxSpeed 时，后续一定会经过减速阶段
            if (currSpeed >= maxSpeed) {
                slowDownDist += currSpeed;
                time++;
                if (speedUpDist + slowDownDist >= k) {
                    return time;
                }
            }
        }
    }
}
