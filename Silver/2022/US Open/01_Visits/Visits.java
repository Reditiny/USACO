import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class Visits {
    static int n;
    // 每个元素有两个属性 [兄弟牛的编号, 拜访时间]
    static List<List<Integer>> graphWithTime = new ArrayList<>();
    static List<List<Integer>> reversedGraph = new ArrayList<>();
    static boolean[] visited;
    static long ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        n = Integer.parseInt(r.readLine());
        for (int i = 0; i < n; i++) {
            graphWithTime.add(new ArrayList<>());
            reversedGraph.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            int nextCow = Integer.parseInt(st.nextToken()) - 1;
            int time = Integer.parseInt(st.nextToken());
            graphWithTime.get(i).add(nextCow);
            graphWithTime.get(i).add(time);
            reversedGraph.get(nextCow).add(i);
            ans += time;
        }
        // 因为每头牛都有一个兄弟牛，所以任意一个起点都可以找到一个环
        // 在这样一张有向图中，只有环内的一头牛无法拜访自己的兄弟牛
        // 所以目标是找到一张连通图中环内的最小拜访时间
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ans -= minInCycle(i);
            }
        }
        pw.println(ans);
        pw.close();
    }

    /**
     * 找到环内最小的拜访时间
     * 因为每个牛都有一个兄弟牛，所以任意一个起点都可以找到一个环
     */
    static int minInCycle(int i) {
        // 判环算法 快慢两个标记 慢的往下走一步 快的往下走两步
        // 如果有环 两者必然会在环内相遇
        int slow = graphWithTime.get(i).get(0);
        int quick = graphWithTime.get(slow).get(0);
        while (slow != quick) {
            slow = graphWithTime.get(slow).get(0);
            quick = graphWithTime.get(graphWithTime.get(quick).get(0)).get(0);
        }
        // 遍历环找到环内最小值
        int minMoo = graphWithTime.get(slow).get(1);
        slow = graphWithTime.get(slow).get(0);
        while (slow != quick) {
            minMoo = Math.min(minMoo, graphWithTime.get(slow).get(1));
            slow = graphWithTime.get(slow).get(0);
        }
        // 标记这张连通图
        dfs(slow);
        return minMoo;
    }

    /**
     * 深度优先遍历将属于本次找环过程的连通图均标记为已访问
     * 这里需要用到反向图 因为进入遍历的起点不一定是连通图的起点
     * 如 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 4
     * 最终从环内出发使用反向图就能完成整个连通图的遍历
     * 1 <- 2 <- 3 <- 4 <- 5 <- 6 <- 4
     */
    static void dfs(int i) {
        if (visited[i]) {
            return;
        }
        visited[i] = true;
        for (Integer child : reversedGraph.get(i)) {
            dfs(child);
        }
    }
}
