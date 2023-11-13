import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class BuildGates {
    static int n;
    // 草地 0 表示未访问 1 表示访问过 2 表示栅栏
    static int[][] grid;
    static int[][] move = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("gates.in"));
        PrintWriter pw = new PrintWriter("gates.out");
        map.put('N', 0);
        map.put('S', 1);
        map.put('W', 2);
        map.put('E', 3);
        n = Integer.parseInt(r.readLine());
        // 这里 2*2 倍的原因是
        // 1.起始点为中心需要两倍来保证每个方向上不会越界
        // 2.每一步都按两步来计算，确保栅栏能分割出有效草地
        // 比如 这样的栅栏虽然圈住了一块草地，但是数组中无法表示这块草地
        //                  ####
        //   ##             #  #
        //   ##             #  #
        //                  ####    扩大到两倍之后中间的草地就可以在数组中表示了
        grid = new int[4 * n + 2][4 * n + 2];
        int startI = 2 * n + 1;
        int startJ = 2 * n + 1;
        grid[startI][startJ] = 2;
        char[] steps = r.readLine().toCharArray();
        for (int i = 0; i < steps.length; i++) {
            int moveDirection = map.get(steps[i]);
            startI += move[moveDirection][0];
            startJ += move[moveDirection][1];
            grid[startI][startJ] = 2;
            startI += move[moveDirection][0];
            startJ += move[moveDirection][1];
            grid[startI][startJ] = 2;
        }
        // 每次广度优先搜索都会将一个块连通的草地标记完成
        // 最终可以得到连通块的数量 - 1 就是需要的门的数量
        int ans = 0;
        for (int i = 0; i < 4 * n + 2; i++) {
            for (int j = 0; j < 4 * n + 2; j++) {
                if (grid[i][j] == 0) {
                    bfs(i, j);
                    ans++;
                }
            }
        }
        pw.println(ans - 1);
        pw.close();
    }

    /**
     * 广度优先搜索
     */
    static void bfs(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        grid[i][j] = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int row = cur[0];
            int col = cur[1];
            for (int k = 0; k < 4; k++) {
                int nextI = row + move[k][0];
                int nextJ = col + move[k][1];
                if (nextI < 0 || nextI >= 4 * n + 2 || nextJ < 0 || nextJ >= 4 * n + 2 || grid[nextI][nextJ] != 0) {
                    continue;
                }
                grid[nextI][nextJ] = 1;
                queue.add(new int[]{nextI, nextJ});
            }
        }

    }
}