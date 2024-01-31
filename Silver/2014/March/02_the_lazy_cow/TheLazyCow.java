import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class TheLazyCow {
    static int N, K;
    static int[][] field;
    static int[][] prefix;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("lazy.in"));
        PrintWriter pw = new PrintWriter("lazy.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // 原本能吃的范围是菱形，如果把菱形旋转 45 度就可以得到正方形
        // 正方形可以更好地使用前缀和
        int newN = 2 * N - 1;
        field = new int[newN][newN];
        for (int row = 0; row < newN; row++) {
            // -1 表示不可用
            Arrays.fill(field[row], -1);
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(r.readLine());
            for (int j = 0; j < N; j++) {
                field[i + j][N - i + j - 1] = Integer.parseInt(st.nextToken());
            }
        }
        prefix = new int[newN + 1][newN + 1];
        for (int i = 0; i < newN; i++) {
            for (int j = 0; j < newN; j++) {
                // 只有大于等于 0 的值才能被吃到
                int val = Math.max(field[i][j], 0);
                prefix[i + 1][j + 1] = (val + prefix[i + 1][j] + prefix[i][j + 1] - prefix[i][j]);
            }
        }
        int ans = 0;
        for (int i = K; i < newN - K; i++) {
            for (int j = K; j < newN - K; j++) {
                // 过滤掉不可用的位置
                if (field[i][j] == -1) {
                    continue;
                }
                // 通过前缀和计算当前位置的矩形范围的草量
                int curGrass =  prefix[i - K][j - K] + prefix[i + K + 1][j + K + 1] - prefix[i + K + 1][j - K] - prefix[i - K][j + K + 1];
                ans = Math.max(ans, curGrass);
            }
        }
        if (K >= N) {
            // check if Bessie can reach everything
            ans = prefix[newN][newN];
        }
        pw.println(ans);
        pw.close();
    }
}