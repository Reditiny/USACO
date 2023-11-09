import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class ClosingTheFarm {
    static int n;
    static int m;
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] closed;
    static boolean[] visited;
    static int[] closeOrder;
    static int visitedCount;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("closing.in"));
        PrintWriter pw = new PrintWriter("closing.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        closeOrder = new int[n];
        closed = new boolean[n];
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(r.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        for (int i = 0; i < n; i++) {
            closeOrder[i] = Integer.parseInt(r.readLine()) - 1;
        }
        // 依次关闭barn，然后从最后一个barn开始dfs查看图的连通性
        for (int i = 0; i < n; i++) {
            visited = new boolean[n];
            visitedCount = 0;
            dfs(closeOrder[n - 1]);
            if (visitedCount == n - i) {
                pw.println("YES");
            } else {
                pw.println("NO");
            }
            closed[closeOrder[i]] = true;
        }
        pw.close();
    }

    /**
     * 深度优先搜索遍历图
     */
    static void dfs(int node) {
        if (visited[node] || closed[node]) {
            return;
        }
        visitedCount++;
        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            dfs(neighbor);
        }
    }

}