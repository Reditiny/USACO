import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * @author Red
 * @version 1.0
 */
public class ConventionII {
    static int N;
    // 记录每头牛的资历、到达时间和耗时
    static Cow[] cows;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("convention2.in"));
        PrintWriter pw = new PrintWriter("convention2.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        N = Integer.parseInt(st.nextToken());
        cows = new Cow[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(r.readLine());
            cows[i] = new Cow(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        // 按到达时间排序
        Arrays.sort(cows, (a, b) -> a.start - b.start);

        int curEndTime = 0;
        int curCow = 0;
        int longestWait = 0;

        // 使用 TreeSet 保存等待的牛，底层会按资历排序
        TreeSet<Cow> waiting = new TreeSet<>();
        while (curCow < N || !waiting.isEmpty()) {
            if (curCow < N && cows[curCow].start <= curEndTime) {
                // 当前牛的开始时间早于正在吃草牛的结束时间
                waiting.add(cows[curCow]);
                curCow++;
            } else if (waiting.size() == 0) {
                // 当前牛的开始时间晚于正在吃草牛的结束时间同时也没有牛在排队
                // 直接吃草
                curEndTime = cows[curCow].start + cows[curCow].duration;
                curCow++;
            } else {
                // 前面有牛在排队
                Cow next = waiting.first();
                longestWait = Math.max(longestWait, curEndTime - next.start);
                // 更新结束时间
                curEndTime += next.duration;
                waiting.remove(waiting.first());
            }
        }
        pw.println(longestWait);
        pw.close();
    }

    static class Cow implements Comparable<Cow> {
        int seniority;
        int start;
        int duration;

        public Cow(int seniority, int start, int duration) {
            this.seniority = seniority;
            this.start = start;
            this.duration = duration;
        }
        // 重写 compareTo 方法，使得 Cow 默认按资历排序
        @Override
        public int compareTo(Cow other) {
            return seniority - other.seniority;
        }
    };
}