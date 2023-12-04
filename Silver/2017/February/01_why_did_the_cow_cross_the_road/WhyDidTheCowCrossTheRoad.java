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
public class WhyDidTheCowCrossTheRoad {
    static int C, N;
    static List<Integer> chickens = new ArrayList<>();
    static List<List<Integer>> cows = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("helpcross.in"));
        PrintWriter pw = new PrintWriter("helpcross.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < C; i++) {
            chickens.add(Integer.parseInt(r.readLine()));
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(r.readLine());
            List<Integer> cow = new ArrayList<>();
            cow.add(Integer.parseInt(st.nextToken()));
            cow.add(Integer.parseInt(st.nextToken()));
            cows.add(cow);
        }
        // 牛按照开始时间排序 鸡按照时间排序
        cows.sort((a, b) -> a.get(0) - b.get(0));
        chickens.sort((a, b) -> a - b);

        int numHelped = 0;
        int cowAt = 0;
        // 优先队列存放结束时间，会自动按照结束时间从小到大排序
        PriorityQueue<Integer> availableCows = new PriorityQueue<>();
        for (int c : chickens) {
            // 可能满足条件的牛的结束时间加入优先队列，即开始时间小于鸡的时间
            while (cowAt < cows.size() && cows.get(cowAt).get(0) <= c) {
                availableCows.add(cows.get(cowAt).get(1));
                cowAt++;
            }
            // 移除不满足条件的牛，即结束时间小于鸡的时间
            while (!availableCows.isEmpty() && availableCows.peek() < c) {
                availableCows.remove();
            }
            // 选择最早结束时间的牛，因为越早结束后面可能的选择就越少，所有优先选择
            if (!availableCows.isEmpty()) {
                numHelped++;
                availableCows.remove();
            }
        }
        pw.println(numHelped);
        pw.close();
    }
}