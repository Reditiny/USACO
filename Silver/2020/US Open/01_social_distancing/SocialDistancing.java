import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class SocialDistancing {
    static int N, M;
    static long[][] intervals;
    static long D;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("socdist.in"));
        PrintWriter pw = new PrintWriter("socdist.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        intervals = new long[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(r.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            intervals[i] = new long[]{a, b};
        }
        // 按区间起点排序后二分查找可以使奶牛全部在草地上的最小距离 D
        Arrays.sort(intervals, (a, b) -> (int) (a[0] - b[0]));
        long left = 0;
        long right = intervals[M - 1][1] - intervals[0][0] + 1;
        while (left < right) {
            // 此处不用 (left + right) / 2 是因为加法操作可能会溢出
            long mid = left + (right - left + 1) / 2;
            if (check(mid)) {
                left = mid;
                D = mid;
            } else {
                right = mid - 1;
            }
        }
        pw.println(D);
        pw.close();
    }

    /**
     * 检查当前距离 D 是否可以使奶牛全部在草地上
     * @param distance
     * @return
     */
    static boolean check(long distance){
        int cowCount = 1;
        int intervalCount = 0;
        long lastPosition = intervals[0][0];
        // 没有到达最后一个区间的右端点之前还可以继续尝试放牛
        while (lastPosition + distance <= intervals[M - 1][1]) {
            // 当前区间的右端点小于当前位置加上距离时，需要跳到下一个区间
            while (lastPosition + distance > intervals[intervalCount][1]) {
                intervalCount++;
            }
            lastPosition = Math.max(intervals[intervalCount][0], lastPosition + distance);
            cowCount++;
            if (cowCount == N) {
                break;
            }
        }
        return cowCount >= N;
    }
}