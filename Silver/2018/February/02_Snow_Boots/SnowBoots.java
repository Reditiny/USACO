import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */

public class SnowBoots {
    static List<List<Integer>> boots = new ArrayList<>();
    static int[] snowDepth;
    static boolean[][] visited;
    static int n, b;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("snowboots.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("snowboots.out"));
        StringTokenizer st = new StringTokenizer(r.readLine());
        n = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        snowDepth = new int[n];
        visited = new boolean[n][b];
        st = new StringTokenizer(r.readLine());
        for (int i = 0; i < n; i++) {
            snowDepth[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < b; i++) {
            st = new StringTokenizer(r.readLine());
            List<Integer> boot = new ArrayList<>();
            boot.add(Integer.parseInt(st.nextToken()));
            boot.add(Integer.parseInt(st.nextToken()));
            boots.add(boot);
        }
        dfs(0, 0);
        pw.println(ans);
        pw.close();
    }

    /**
     * 在 tile 砖块上穿着 boot 鞋子
     * 深度优先搜索遍历可能的[砖块，鞋子]组合
     */
    private static void dfs(int tile, int boot) {
        if (visited[tile][boot]) {
            return;
        }
        visited[tile][boot] = true;
        // 到达最后一块有雪的砖，现在鞋子的编号就是已经丢掉的鞋子的数量
        if (tile == n - 1) {
            ans = Math.min(ans, boot);
        }
        // 从当前砖块出发，穿当前的鞋子，尝试所有可能的下一个砖块
        for (int nextTile = tile + 1; nextTile < n && nextTile - tile <= boots.get(boot).get(1); nextTile++) {
            if (snowDepth[nextTile] <= boots.get(boot).get(0)) {
                dfs(nextTile, boot);
            }
        }
        // 在当前砖块上，尝试所有可能的鞋子
        for (int nextBoot = boot + 1; nextBoot < boots.size(); nextBoot++) {
            if (snowDepth[tile] <= boots.get(nextBoot).get(0)) {
                dfs(tile, nextBoot);
            }
        }
    }

}