import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class ComfortableCows {
    static int n;
    static final boolean[][] grid = new boolean[3000][3000];
    static final int[][] adjCount = new int[3000][3000];
    static int[][] move = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        n = Integer.parseInt(r.readLine());
        for (int j = 0; j < n; j++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            int x = Integer.parseInt(st.nextToken()) + 1000;
            int y = Integer.parseInt(st.nextToken()) + 1000;
            add(x, y);
            answer--;
            pw.println(answer);
        }
        pw.close();
    }

    /**
     * 在 x y 位置放置一头牛并在函数内处理该牛带来的影响
     */
    static void add(int x, int y) {
        // 如果原本已经有一头牛在这个位置了，那么就不需要再放置了
        if (!grid[x][y]) {
            grid[x][y] = true;
            answer++;
            // 如果这个位置周围有三头牛，那么就在剩下一个方向上也放一头牛
            // 因为已经有三头牛了，所以循环会直接找到该放的位置
            if (adjCount[x][y] == 3) {
                for (int i = 0; i < 4; i++) {
                    int u = x + move[i][0];
                    int v = y + move[i][1];
                    add(u, v);
                }
            }
            // 新放的牛也可能会导致周围的牛变成舒适
            for (int i = 0; i < 4; i++) {
                int u = x + move[i][0];
                int v = y + move[i][1];
                adjCount[u][v]++;
                // 所以要检查周围的牛是否舒适
                // 如果周围的牛舒适，那么这头牛的剩下一个方向上放一头牛
                if (grid[u][v] && adjCount[u][v] == 3) {
                    for (int j = 0; j < 4; j++) {
                        int w = u + move[j][0];
                        int z = v + move[j][1];
                        add(w, z);
                    }
                }
            }
        }
    }
}