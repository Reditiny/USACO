import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class WhyDidTheCowCrossTheRoadIII {
    static List<List<Integer>> cows = new ArrayList<List<Integer>>();

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("cowqueue.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        int n = Integer.parseInt(r.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            List<Integer> cow = new ArrayList<>();
            cow.add(Integer.parseInt(st.nextToken()));
            cow.add(Integer.parseInt(st.nextToken()));
            cows.add(cow);
        }
        // 先来后到排序 同时到按答题时长排序
        cows.sort((a, b) -> {
            if (a.get(0).equals(b.get(0))) {
                return a.get(1) - b.get(1);
            }
            return a.get(0) - b.get(0);
        });
        int ans = 0;
        for (int i = 0; i < cows.size(); i++) {
            if (cows.get(i).get(0) >= ans) {
                // 这头牛到达时间比上一头牛结束时间更晚，到了才能答题
                ans = cows.get(i).get(0) + cows.get(i).get(1);
            } else {
                // 这头牛到达时间比上一头牛结束时间更早，等上头牛答完才能答题
                ans += cows.get(i).get(1);
            }
        }
        pw.println(ans);
        pw.close();
    }
}
