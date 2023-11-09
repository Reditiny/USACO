import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class Moocast {
    static int n;
    static int[][] cowPositions;
    static int[] powers;
    static boolean[] visited;
    static int visitedCount;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("moocast.in"));
        PrintWriter pw = new PrintWriter("moocast.out");
        n = Integer.parseInt(r.readLine());
        cowPositions = new int[n][2];
        powers = new int[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            cowPositions[i][0] = Integer.parseInt(st.nextToken()) - 1;
            cowPositions[i][1] = Integer.parseInt(st.nextToken()) - 1;
            powers[i] = Integer.parseInt(st.nextToken());
        }
        // 从每头牛开始遍历，计算每头牛能到达的牛的数量，取最大值
        int ans = 0;
        for (int i = 0; i < n; i++) {
            visited = new boolean[n];
            visitedCount = 0;
            dfs(i);
            ans = Math.max(ans, visitedCount);
        }
        pw.println(ans);
        pw.close();
    }
    /**
     * 深度优先搜索遍历
     */
    static void dfs(int i) {
        if (visited[i]) {
            return;
        }
        visited[i] = true;
        visitedCount++;
        for (int j = 0; j < n; j++) {
            if (i != j && !visited[j] && distance(cowPositions[i], cowPositions[j]) <= powers[i]) {
                dfs(j);
            }
        }
    }

    /**
     * 计算两点之间的距离
     */
    static double distance(int[] a, int[] b) {
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }
}