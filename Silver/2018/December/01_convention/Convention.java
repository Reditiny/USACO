import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class Convention {
    static int N, M, C;
    static int[] cows;
    static int ans;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("convention.in"));
        PrintWriter pw = new PrintWriter("convention.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        cows = new int[N];
        st = new StringTokenizer(r.readLine());
        for (int i = 0; i < N; i++) {
            cows[i] = Integer.parseInt(st.nextToken());
        }
        // 排序后二分查找
        Arrays.sort(cows);
        int left = 0;
        int right = cows[N - 1];
        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(mid)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        pw.println(ans);
        pw.close();
    }

    /**
     * 检查 M 辆车能否在 minWait 时间内运送完毕
     */
    static boolean check(int minWait) {
        int first = cows[0];
        int used = 1;
        int curCap = 0;
        for (int i = 0; i < N; i++) {
            // 每辆车到时间或者牛满就发车
            if (cows[i] - first > minWait || curCap >= C) {
                used++;
                curCap = 0;
                first = cows[i];
            }
            curCap++;
        }
        return used <= M;
    }
}