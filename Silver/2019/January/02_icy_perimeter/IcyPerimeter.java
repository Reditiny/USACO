import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Red
 * @version 1.0
 */
public class IcyPerimeter {
    static int n;
    static char[][] grid;
    static boolean[][] visited;
    // 搜索的四个方向
    static int[][] move = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("perimeter.in"));
        PrintWriter pw = new PrintWriter("perimeter.out");
        n = Integer.parseInt(r.readLine());
        grid = new char[n][n];
        visited = new boolean[n][n];
        for (int row = 0; row < n; row++) {
            String line = r.readLine();
            for (int col = 0; col < n; col++) {
                grid[row][col] = line.charAt(col);
            }
        }
        int ansPerimeter = 0, ansArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '#' && !visited[i][j]) {
                    int[] curAns = bfs(i, j);
                    if (curAns[0] > ansArea) {
                        ansArea = curAns[0];
                        ansPerimeter = curAns[1];
                    } else if (curAns[0] == ansArea) {
                        ansPerimeter = Math.min(ansPerimeter, curAns[1]);
                    }
                }
            }
        }
        pw.println(ansArea + " " + ansPerimeter);
        pw.close();
    }

    /**
     * 广度优先搜索 在搜索的同时计算面积和周长
     */
    static int[] bfs(int i, int j) {
        int[] ans = new int[2];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        ans[0]++;
        ans[1] += getPerimeter(i, j);
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int row = cur[0];
            int col = cur[1];
            for (int k = 0; k < 4; k++) {
                int newRow = row + move[k][0];
                int newCol = col + move[k][1];
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && grid[newRow][newCol] == '#' && !visited[newRow][newCol]) {
                    queue.add(new int[]{newRow, newCol});
                    ans[0]++;
                    ans[1] += getPerimeter(newRow, newCol);
                    visited[newRow][newCol] = true;
                }
            }
        }
        return ans;
    }

    /**
     * 计算某点提供的周长 如果某点的上下左右有空白或者超出边界，那么周长加一
     */
    static int getPerimeter(int row, int col) {
        int perimeter = 0;
        for (int i = 0; i < 4; i++) {
            int newRow = row + move[i][0];
            int newCol = col + move[i][1];
            if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= n || grid[newRow][newCol] == '.') {
                perimeter++;
            }
        }
        return perimeter;
    }
}