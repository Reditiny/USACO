import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class RectangularPasture {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(r.readLine());
        int[][] cows = new int[N][2];
        for (int c = 0; c < N; c++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            cows[c][0] = Integer.parseInt(st.nextToken());
            cows[c][1] = Integer.parseInt(st.nextToken());
        }
        // 坐标压缩 把横纵坐标分按顺序设置为 0 1 2 3 ...
        // 这样不会改变原来点与点之间的相对位置，同时使点数量的计算更加简单
        // (0,2) (1,0) (2,3) (3,5) -> (0,1) (1,0) (2,2) (3,3)
        Arrays.sort(cows, Comparator.comparingInt(c -> c[0]));
        for (int c = 0; c < N; c++) { cows[c][0] = c; }
        Arrays.sort(cows, Comparator.comparingInt(c -> c[1]));
        for (int c = 0; c < N; c++) { cows[c][1] = c; }
        // 求的y轴点数量的前缀和
        // ltY[x][y] 表示横纵坐标比(x,y)小的点的数量
        Arrays.sort(cows, Comparator.comparingInt(c -> c[0]));
        int[][] ltY = new int[N+1][N];
        int[][] gtY = new int[N+1][N];
        for (int c = 0; c < N; c++) {
            int currY = cows[c][1];
            for (int x = 1; x <= N; x++) {
                ltY[x][currY] = (ltY[x - 1][currY] + (cows[x - 1][1] < currY ? 1 : 0));
                gtY[x][currY] = (gtY[x - 1][currY] + (cows[x - 1][1] > currY ? 1 : 0));
            }
        }
        long total = 0;
        // 包含至少两个点的集合
        for (int c1 = 0; c1 < N; c1++) {
            for (int c2 = c1 + 1; c2 < N; c2++) {
                int bottom = Math.min(cows[c1][1], cows[c2][1]);
                int top = Math.max(cows[c1][1], cows[c2][1]);
                // 对于任意两点作为矩形的左下角和右上角
                // a 为矩形上方的点的数量 b 为矩形下方的点的数量
                // (a + 1) * (b + 1) 为包含这两个点并以这两个点的横坐标为界的不同集合数量
                int a = 1 + ltY[c2 + 1][bottom] - ltY[c1][bottom];
                int b = 1 + gtY[c2 + 1][top] - gtY[c1][top];
                total += (long)a * b;
            }
        }
        // 只包含一个点的集合和空集
        total += N + 1;

        System.out.println(total);
    }
}
