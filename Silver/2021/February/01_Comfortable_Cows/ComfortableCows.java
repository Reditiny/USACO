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
    // 此处预留 3000*3000 的空间，原本牛的范围是 1000*1000
    // 新牛的位置不受原空间限制，简单考虑就是所有新牛都在一个方向上扩展，最多 1000 个位置
    // 因此四个方向各扩展 1000 个位置，一定不会出现越界
    // 当然由于新牛的位置会受到 comfortable 条件的限制，所以实际上不会扩展到 3000*3000
    // 这样只是以最快的速度找到一个可行的边界
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
                // 此处不需要检查边界，因为 grid 的预留空间已经是可能解的上界了，所以不可能越界
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
                // 所以要检查周围的牛是否舒适，如果舒适那么这头牛的剩下一个方向上放一头牛
                // grid[u][v] 检查这个位置有没有牛，adjCount[u][v] == 3 检查牛是否舒适
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