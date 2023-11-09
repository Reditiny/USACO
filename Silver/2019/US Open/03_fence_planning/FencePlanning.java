import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class FencePlanning {
    static int n, m;
    static List<List<Integer>> graph = new ArrayList<>();
    static int[][] cowPositions;
    static boolean[] visited;
    static int minX, maxX, minY, maxY;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("fenceplan.in"));
        PrintWriter pw = new PrintWriter("fenceplan.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cowPositions = new int[n][2];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            st = new StringTokenizer(r.readLine());
            cowPositions[i][0] = Integer.parseInt(st.nextToken());
            cowPositions[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(r.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // 此处不需要回溯 visited 的状态
            // 因为已经遍历到的节点，它所在的连通图的最大最小x y坐标已经确定
            // 若回溯状态则会导致超时
            minX = Integer.MAX_VALUE;
            maxX = 0;
            minY = Integer.MAX_VALUE;
            maxY = 0;
            dfs(i);
            if (maxX > minX && maxY > minY) {
                ans = Math.min(ans, 2 * (maxX - minX + maxY - minY));
            }
        }
        pw.println(ans);
        pw.close();

    }

    /**
     * 深度优先搜索遍历
     * 遍历过程中寻找当前连通图的最大最小x y坐标
     */
    static void dfs(int i) {
        if (visited[i]) {
            return;
        }
        visited[i] = true;
        minX = Math.min(minX, cowPositions[i][0]);
        maxX = Math.max(maxX, cowPositions[i][0]);
        minY = Math.min(minY, cowPositions[i][1]);
        maxY = Math.max(maxY, cowPositions[i][1]);
        for (int neighbor : graph.get(i)) {
            dfs(neighbor);
        }
    }
}