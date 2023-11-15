import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class MultiplayerMoo {
    static int N;
    static int[][] grid;
    static boolean[][] visited;
    static int[][] moves = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // 记录每个区域的所有点
    static List<List<List<Integer>>> regionCells = new ArrayList<List<List<Integer>>>();
    // 记录每个区域的id
    static List<Integer> regionIds = new ArrayList<>();
    // 记录每个区域相邻的区域
    static List<HashSet<Integer>> adjoinRegions = new ArrayList<>();
    static int ansSingle = 0;
    static int ansTeam = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("multimoo.in"));
        PrintWriter pw = new PrintWriter("multimoo.out");
        N = Integer.parseInt(r.readLine());
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        countSingle();
        getAdjoinRegions();
        countTeam();
        pw.println(ansSingle);
        pw.println(ansTeam);
        pw.close();
    }

    /**
     * 广度优先搜索遍历每个区域 并记录每个区域的所有点
     */
    static void countSingle() {
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfsSingle(i, j, grid[i][j]);
                }
            }
        }
    }

    /**
     * 迭代法广度优先搜索
     */
    static void bfsSingle(int i, int j, int cur) {
        List<List<Integer>> cells = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        cells.add(Arrays.asList(i, j));
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            for (int[] move : moves) {
                int nextR = curPos[0] + move[0];
                int nextC = curPos[1] + move[1];
                if (nextR >= 0 && nextR < N && nextC >= 0 && nextC < N && !visited[nextR][nextC] && grid[nextR][nextC] == cur) {
                    queue.add(new int[]{nextR, nextC});
                    cells.add(Arrays.asList(nextR, nextC));
                    visited[nextR][nextC] = true;
                }
            }
        }
        regionCells.add(cells);
        regionIds.add(cur);
        ansSingle = Math.max(ansSingle, cells.size());
    }

    /**
     * 根据每个区域的所有点 记录每个区域相邻的区域
     */
    static void getAdjoinRegions() {
        for (int i = 0; i < regionCells.size(); i++) {
            adjoinRegions.add(new HashSet<>());
        }
        for (List<List<Integer>> cells : regionCells) {
            for (List<Integer> cell : cells) {
                for (int[] move : moves) {
                    int nextR = cell.get(0) + move[0];
                    int nextC = cell.get(1) + move[1];
                    if (nextR >= 0 && nextR < N && nextC >= 0 && nextC < N) {
                        for (int i = 0; i < regionCells.size(); i++) {
                            if (regionCells.get(i).contains(Arrays.asList(nextR, nextC))) {
                                adjoinRegions.get(i).add(regionCells.indexOf(cells));
                                adjoinRegions.get(regionCells.indexOf(cells)).add(i);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 以区域为单位进行广度优先搜索
     */
    static void countTeam() {
        HashSet<int[]> processed = new HashSet<>();
        for (int r1 = 0; r1 < regionCells.size(); r1++) {
            for (int r2 : adjoinRegions.get(r1)) {
                if (r2 > r1 && !processed.contains(new int[]{r2, r1})) {
                    int r1Id = regionIds.get(r1);
                    int r2Id = regionIds.get(r2);
                    processed.add(new int[]{r2, r1});
                    int teamSize = 0;
                    boolean[] teamVisited = new boolean[regionCells.size()];
                    Queue<Integer> queue = new LinkedList<>();
                    queue.add(r1);
                    teamVisited[r1] = true;
                    while (!queue.isEmpty()) {
                        int cur = queue.poll();
                        teamSize += regionCells.get(cur).size();
                        for (int next : adjoinRegions.get(cur)) {
                            int nextId = regionIds.get(next);
                            if (!teamVisited[next] && (nextId == r1Id || nextId == r2Id)) {
                                queue.add(next);
                                teamVisited[next] = true;
                            }
                        }
                    }
                    ansTeam = Math.max(ansTeam, teamSize);
                }
            }
        }
    }
}