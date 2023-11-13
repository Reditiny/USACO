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
public class WhyDidTheCowCrossTheRoadIII {
    static int n, k, r;
    // 草地 -1 表示路 0 表示未访问 1，2，3...表示连通图编号
    static int[][] grid;
    static int[][] move = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static List<List<Integer>> cows = new ArrayList<List<Integer>>();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("countcross.in"));
        PrintWriter pw = new PrintWriter("countcross.out");
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        // 将草地扩大两倍 可以使得路和草地都可以在数组表示
        // 例如 原本两个相邻的草地 路没法在数组中表示 扩大后可以表示
        //
        // ##  扩大   ####  路   #｜##
        //     ->    ####  ->   #｜##
        grid = new int[2 * n][2 * n];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(reader.readLine());
            int r1 = Integer.parseInt(st.nextToken()) - 1;
            int c1 = Integer.parseInt(st.nextToken()) - 1;
            int r2 = Integer.parseInt(st.nextToken()) - 1;
            int c2 = Integer.parseInt(st.nextToken()) - 1;
            if (r1 == r2) {
                grid[2 * r1][c1 + c2] = -1;
                grid[2 * r1 + 1][c1 + c2] = -1;
            } else {
                grid[r1 + r2][2 * c1] = -1;
                grid[r1 + r2][2 * c1 + 1] = -1;
            }
        }
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(reader.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            cows.add(Arrays.asList(r * 2, c * 2));
        }
        // 广度优先搜索连通图同时标记连通图编号
        int regionCount = 1;
        for (int i = 0; i < 2 * n; i++) {
            for (int j = 0; j < 2 * n; j++) {
                if (grid[i][j] == 0) {
                    dfs(i, j, regionCount);
                    regionCount++;
                }
            }
        }
        // 比较每两个牛的位置是否在同一个连通图中
        int ans = 0;
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {
                if (grid[cows.get(i).get(0)][cows.get(i).get(1)] != grid[cows.get(j).get(0)][cows.get(j).get(1)]) {
                    ans++;
                }
            }
        }
        pw.println(ans);
        pw.close();
    }

    /**
     * 深度优先搜索同时标记连通图编号
     */
    static void dfs(int i, int j, int regionCount) {
        grid[i][j] = regionCount;
        for (int[] m : move) {
            int nextI = i + m[0];
            int nextJ = j + m[1];
            if (nextI >= 0 && nextI < 2 * n && nextJ >= 0 && nextJ < 2 * n && grid[nextI][nextJ] == 0) {
                dfs(nextI, nextJ, regionCount);
            }
        }
    }
}