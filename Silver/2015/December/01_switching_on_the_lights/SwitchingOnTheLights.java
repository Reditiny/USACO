import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class SwitchingOnTheLights {
    static int n, m;
    static boolean[][] lights;
    // 房间有3种状态 0 没访问过 1 没访问过但是曾路过(因为灯没亮) 2 访问过
    // 设置 1 状态的原因是可能有的房间很早就遍历到了但是后来才开灯，需要用这个状态来回溯
    static int[][] visited;
    static Map<List<Integer>, List<List<Integer>>> roomToSwitchers = new HashMap<>();
    static int[][] move = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("lightson.in"));
        PrintWriter pw = new PrintWriter("lightson.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        n = Integer.parseInt(st.nextToken());
        lights = new boolean[n][n];
        lights[0][0] = true;
        visited = new int[n][n];
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(r.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            List<Integer> room = Arrays.asList(x, y);
            List<Integer> switcher = Arrays.asList(a, b);
            if (!roomToSwitchers.containsKey(room)) {
                roomToSwitchers.put(room, new ArrayList<List<Integer>>());
            }
            roomToSwitchers.get(room).add(switcher);
        }
        bfs(0, 0);
        int ans = 0;
        for (boolean[] row : lights) {
            for (boolean light : row) {
                if (light) {
                    ans++;
                }
            }
        }

        pw.println(ans);
        pw.close();
    }

    /**
     * 广度优先搜索
     */
    static int bfs(int x, int y) {
        int ans = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = 2;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int row = cur[0];
            int col = cur[1];
            // 每到一个房间，就把这个房间的开关打开
            if (roomToSwitchers.containsKey(Arrays.asList(row, col))) {
                for (List<Integer> switcher : roomToSwitchers.get(Arrays.asList(row, col))) {
                    // 如果这个开关对应的房间没亮，那么就亮起来
                    if (!lights[switcher.get(0)][switcher.get(1)]) {
                        lights[switcher.get(0)][switcher.get(1)] = true;
                    }
                    // 如果这个房间曾经路过，现在灯亮了，那么就把这个房间加入队列
                    if (visited[switcher.get(0)][switcher.get(1)] == 1) {
                        queue.add(new int[]{switcher.get(0), switcher.get(1)});
                    }
                }
            }
            for (int[] next : move) {
                int nextRow = row + next[0];
                int nextCol = col + next[1];
                if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n && visited[nextRow][nextCol] != 2) {
                    if (!lights[nextRow][nextCol]) {
                        visited[nextRow][nextCol] = 1;
                    } else {
                        queue.add(new int[]{nextRow, nextCol});
                        visited[nextRow][nextCol] = 2;
                    }
                }
            }
        }
        return ans;
    }
}