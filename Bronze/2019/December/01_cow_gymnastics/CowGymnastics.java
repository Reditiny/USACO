import java.io.*;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class CowGymnastics {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("gymnastics.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("gymnastics.out"));
        StringTokenizer st = new StringTokenizer(r.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] cows = new int[k][n];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(r.readLine());
            for (int j = 0; j < n; j++) {
                cows[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 遍历所有组合中的每一对奶牛，判断 i 是否比 j 好
        boolean[][] better = new boolean[n][n];
        for (int l = 0; l < k; l++) {
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    better[cows[l][i] - 1][cows[l][j] - 1] = true;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (better[i][j] != better[j][i]) {
                    ans++;
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}
