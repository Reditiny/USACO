import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class CowDanceShow {
    static int N, T;
    static List<Integer> durations = new ArrayList<>();
    static int K;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("cowdance.in"));
        PrintWriter pw = new PrintWriter("cowdance.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            durations.add(Integer.parseInt(r.readLine()));
        }
        // 二分查找可行的 K
        int left = 1, right = N;
        while (left < right) {
            int mid = (left + right) / 2;
            if (check(mid)) {
                right = mid;
                K = mid;
            } else {
                left = mid + 1;
            }
        }
        pw.println(K);
        pw.close();
    }

    /**
     * 检查舞台大小为 stageSize 是否能在 T 之前结束
     */
    static boolean check(int stageSize) {
        // 优先队列，底层会自动按照元素的自然顺序排列
        PriorityQueue<Integer> currentDancing = new PriorityQueue<>();
        for (int cow = 0; cow < N; cow++) {
            if (currentDancing.size() < stageSize) {
                // 舞台未满，直接加入当前牛的结束时间
                currentDancing.add(durations.get(cow));
            } else {
                // 舞台已满，当前牛的结束时间为舞台中最早结束的牛的结束时间加上当前牛的耗时
                currentDancing.add(currentDancing.remove() + durations.get(cow));
            }
        }
        int lastFinish = Integer.MAX_VALUE;
        while (currentDancing.size() > 0) {
            lastFinish = currentDancing.remove();
        }
        return lastFinish <= T;
    }
}