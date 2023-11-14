import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class MooyoMooyo {
    static int n, k;
    static int[][] grid;
    static boolean[][] visited;
    static int[][] move = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("mooyomooyo.in"));
        PrintWriter pw = new PrintWriter("mooyomooyo.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        grid = new int[n][10];
        for (int i = 0; i < n; i++) {
            String line = r.readLine();
            for (int j = 0; j < 10; j++) {
                grid[i][j] = line.charAt(j) - '0';
            }
        }
        while (true) {
            boolean flag = false;
            visited = new boolean[n][10];
            // 搜索所有连通图
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 10; j++) {
                    if (grid[i][j] != 0 && !visited[i][j]) {
                        int count = dfs(i, j, grid[i][j]);
                        if (count >= k) {
                            // 连通图大小至少为k则将数字置为0
                            flag = true;
                            disappearBfs(i, j, grid[i][j]);
                        }
                    }
                }
            }
            if (!flag) {
                break;
            }
            fall();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                pw.print(grid[i][j]);
            }
            pw.println();
        }
        pw.close();
    }

    /**
     * 深度搜索并获得连通图大小
     */
    static int dfs(int i, int j, int color) {
        visited[i][j] = true;
        int count = 1;
        for (int[] m : move) {
            int x = i + m[0];
            int y = j + m[1];
            if (x >= 0 && x < n && y >= 0 && y < 10 && !visited[x][y] && grid[x][y] == color) {
                count += dfs(x, y, color);
            }
        }
        return count;
    }

    /**
     * 广度搜索并将连通图中的数字置为0
     */
    static void disappearBfs(int i, int j, int color) {
        grid[i][j] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int row = cur[0];
            int col = cur[1];
            for (int[] m : move) {
                int x = row + m[0];
                int y = col + m[1];
                if (x >= 0 && x < n && y >= 0 && y < 10 && grid[x][y] == color) {
                    grid[x][y] = 0;
                    queue.add(new int[]{x, y});
                }
            }
        }
    }

    /**
     * 模拟重力下落
     */
    static void fall() {
        for (int i = 0; i < 10; i++) {
            int[] column = new int[n];
            int index = n - 1;
            for (int j = n - 1; j >= 0; j--) {
                if (grid[j][i] != 0) {
                    column[index] = grid[j][i];
                    index--;
                }
            }
            for (int j = 0; j < n; j++) {
                grid[j][i] = column[j];
            }
        }
    }
}