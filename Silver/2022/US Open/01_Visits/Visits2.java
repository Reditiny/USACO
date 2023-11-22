import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class Visits2 {
    static int n;
    // 每个元素有两个属性 [兄弟牛的编号, 拜访时间]
    static List<List<Integer>> graphWithTime = new ArrayList<>();
    static boolean[] visited;
    static long ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        n = Integer.parseInt(r.readLine());
        for (int i = 0; i < n; i++) {
            graphWithTime.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            int nextCow = Integer.parseInt(st.nextToken()) - 1;
            int time = Integer.parseInt(st.nextToken());
            graphWithTime.get(i).add(nextCow);
            graphWithTime.get(i).add(time);
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
     * 从位置 i 开始寻找环同时标记访问过的位置避免重复访问
     * 当遍历到环的入口时，找出环内最小值
     */
    static int minInCycle(int i) {
        // 通过 set 找到环的入口
        int cur = i;
        HashSet<Integer> cycleCow = new HashSet<Integer>();
        cycleCow.add(i);
        visited[i] = true;
        while (true) {
            cur = graphWithTime.get(cur).get(0);
            // 如果 cur 已经在 set 中，说明此处为环的入口
            if (cycleCow.contains(cur)) {
                break;
            }
            // 如果 cur 已经被访问过，但是却没有在 set 中
            // 说明后续的点都已经访问过，环内的最小值也已经计算过了
            if(visited[cur]){
                return 0;
            }
            cycleCow.add(cur);
            visited[cur] = true;
        }
        // 此时 cur 在环中，从 cur 出发找到环内最小值
        int min = graphWithTime.get(cur).get(1);
        int next = graphWithTime.get(cur).get(0);
        while(next != cur){
            min = Math.min(min, graphWithTime.get(next).get(1));
            next = graphWithTime.get(next).get(0);
        }
        return min;
    }
}
