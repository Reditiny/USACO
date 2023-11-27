import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class StampGrid {
    static int T, N, K;
    static char[][] grid;
    static char[][][] stamps;
    static char[][] curGrid;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        T = Integer.parseInt(r.readLine());
        for (int t = 0; t < T; t++) {
            r.readLine();
            N = Integer.parseInt(r.readLine());
            grid = new char[N][N];
            curGrid = new char[N][N];
            for (int row = 0; row < N; row++) {
                grid[row] = r.readLine().toCharArray();
                Arrays.fill(curGrid[row],'.');
            }
            K = Integer.parseInt(r.readLine());
            stamps = new char[4][K][K];
            for (int row = 0; row < K; row++) {
                stamps[0][row] = r.readLine().toCharArray();
            }
            rotate();
            if (validate()) {
                pw.println("YES");
            } else {
                pw.println("NO");
            }
        }
        pw.close();
    }

    /**
     * 获得所有旋转后的 stamp
     */
    static void rotate(){
        for(int t = 0; t < 3; t++){
            for(int i = 0; i < K; i++) {
                for(int j = 0; j < K; j++) {
                    stamps[t+1][i][j] = stamps[t][K - j - 1][i];
                }
            }
        }
    }

    /**
     * 检查能否成功打印
     * 将 stamp 从左上角开始在 grid 上移动，再依次旋转 stamp
     * 在当前的 stamp 范围内，grid 上的 '.' 与 stamp 上的 '*' 不能同时出现，grid 上的 '*' 与 stamp 上的 '.' 可以同时出现
     * 因为 grid 上的 '*' 可以在之后的移动中被覆盖，但是 grid 上的 '.' 始终不能被覆盖
     */
    static boolean validate() {
        for (int stamp = 0; stamp < 4; stamp++) {
            for (int row = 0; row <= N - K; row++) {
                for (int col = 0; col <= N - K; col++) {
                    paint(stamp, row, col);
                }
            }
            if (Arrays.deepEquals(grid, curGrid)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 用当前 stamp 以 (row,col) 为左上角打印图案
     */
    static void paint(int stamp, int row, int col) {
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                // 如果当前 stamp 的范围内，grid 上的 '.' 与 stamp 上的 '*' 同时出现，说明不能打印
                if(grid[row + i][col + j] == '.' && stamps[stamp][i][j] == '*'){
                    return;
                }
            }
        }
        // 将 stamp 的 '*' 打印到 grid 上
        for(int i = 0; i < K; i++) {
            for(int j = 0; j < K; j++) {
                if(stamps[stamp][i][j] == '*') {
                    curGrid[row + i][col + j] = '*';
                }
            }
        }
    }

}