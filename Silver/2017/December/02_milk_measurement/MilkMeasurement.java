import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class MilkMeasurement {
    static int N,G;
    // 日志集合 [日期,牛号,改变量]
    static List<List<Integer>> logs = new ArrayList<>();
    // 记录牛到产量的映射
    static Map<Integer,Integer> cowToMilk = new HashMap<>();
    // 记录产量到数量的映射 TreeMap 底层会自动排序
    static TreeMap<Integer, Integer> milkProd = new TreeMap<>();
    static int ans = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("measurement.in"));
        PrintWriter pw = new PrintWriter("measurement.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        N = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(r.readLine());
            int day = Integer.parseInt(st.nextToken());
            int cow = Integer.parseInt(st.nextToken());
            int change = Integer.parseInt(st.nextToken());
            logs.add(new ArrayList<Integer>(){{add(day);add(cow);add(change);}});
            cowToMilk.put(cow,G);
        }
        // 按日期排序
        logs.sort((a,b)->a.get(0)-b.get(0));
        milkProd.put(G, N);
        // 依次遍历日志计算改变量
        for (List<Integer> log : logs) {
            // 该牛的原产量，该产量是不是上一次的最高产量，该产量的数量
            int milkMount = cowToMilk.get(log.get(1));
            boolean wasTop = milkMount == milkProd.lastKey();
            int prevCount = milkProd.get(milkMount);
            // 该牛的产量改变了，产量到数量的映射相应减1
            milkProd.put(milkMount, milkProd.get(milkMount) - 1);
            if (milkProd.get(milkMount) == 0) { milkProd.remove(milkMount); }
            // 更新该牛的产量以及产量到数量的映射
            milkMount += log.get(2);
            cowToMilk.put(log.get(1), milkMount);
            milkProd.put(milkMount, milkProd.getOrDefault(milkMount, 0) + 1);
            // 新产量是不是当前最高产量，当前产量的数量
            boolean isTop = milkMount == milkProd.lastKey();
            int currCount = milkProd.get(milkMount);
            if (wasTop) {
                // 该牛产量原来是最高的
                if (isTop && currCount == prevCount) {
                    // 该牛产量改变后也是最高的，而且改变前后只有它一个牛产量最高，不需要改变
                    continue;
                }
                ans++;
            } else if (isTop) {
                // 原来不是最高，现在是最高
                ans++;
            }
        }
        pw.println(ans);
        pw.close();
    }
}