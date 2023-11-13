import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class CrossCountrySkiing {
    static int M, N,D;
    static int[][] grid;
    static int minHeight = Integer.MAX_VALUE;
    static int maxHeight = Integer.MIN_VALUE;
    static boolean[][] wayPoints;
    static int startI, startJ;
    static boolean[][] visited;
    static int[][] moves = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("ccski.in"));
        PrintWriter pw = new PrintWriter("ccski.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        grid = new int[M][N];
        wayPoints = new boolean[M][N];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(r.readLine());
            for(int j = 0; j < N; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
                minHeight = Math.min(minHeight, grid[i][j]);
                maxHeight = Math.max(maxHeight, grid[i][j]);
            }
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(r.readLine());
            for(int j = 0; j < N; j++){
                if(Integer.parseInt(st.nextToken()) == 1){
                    wayPoints[i][j] = true;
                    startI = i;
                    startJ = j;
                }
            }
        }
        // 二分查找最小的D
        int left = 0, right = maxHeight - minHeight;
        while(left <= right){
            int mid = (left + right) / 2;
            visited = new boolean[M][N];
            dfs(startI, startJ, mid);
            if(check()){
                D = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        pw.println(D);
        pw.close();
    }

    /**
     * 深度优先搜索 从起点开始遍历所有可达点
     */
    static void dfs(int i, int j, int d) {
        if (visited[i][j]) { return; }
        visited[i][j] = true;
        for(int k=0; k < 4; k++) {
            int nextI = i + moves[k][0];
            int nextJ = j + moves[k][1];
            if (nextI >= 0 && nextI < M && nextJ >= 0 && nextJ < N && !visited[nextI][nextJ] && Math.abs(grid[i][j] - grid[nextI][nextJ]) <= d) {
                dfs(nextI, nextJ, d);
            }
        }
    }
    /**
     * 检查所有wayPoints是否都被访问过
     */
    static boolean check(){
        for(int i=0; i < M; i++) {
            for(int j=0; j < N; j++) {
                if(wayPoints[i][j] && !visited[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}