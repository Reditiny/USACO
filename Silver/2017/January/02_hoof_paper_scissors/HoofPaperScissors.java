import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class HoofPaperScissors {
    static int N;
    // winGestures 存储获胜的手势 0 表示 P  1 表示 S  2 表示 H
    static int[] winGestures;
    static int[][] prefixWin;
    static int[][] suffixWin;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("hps.in"));
        PrintWriter pw = new PrintWriter("hps.out");
        N = Integer.parseInt(r.readLine());
        winGestures = new int[N];
        prefixWin = new int[3][N + 1];
        suffixWin = new int[3][N + 1];
        for (int i = 0; i < N; i++) {
            String c = r.readLine();
            // 当输入为 H 时，P 为获胜手势，所以存 0
            switch (c) {
                case "H":
                    winGestures[i] = 0;
                    break;
                case "P":
                    winGestures[i] = 1;
                    break;
                case "S":
                    winGestures[i] = 2;
                    break;
            }
        }
        // 前缀胜利和
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < 3; j++) {
                prefixWin[j][i+1] = prefixWin[j][i];
            }
            int winGesture = winGestures[i];
            prefixWin[winGesture][i+1] = prefixWin[winGesture][i] + 1;
        }
        // 后缀胜利和
        for(int i = N-1; i >= 0; i--) {
            for(int j = 0; j < 3; j++) {
                suffixWin[j][i] = suffixWin[j][i+1];
            }
            int winGesture = winGestures[i];
            suffixWin[winGesture][i] = suffixWin[winGesture][i+1] + 1;
        }
        // 枚举分割点 i 前面用前缀算 后面用后缀算
        int ans = 0;
        for(int i = 0; i <= N; i++) {
            for(int p = 0; p < 3; p++) {
                for(int s = 0; s < 3; s++) {
                    int cur = prefixWin[p][i] + suffixWin[s][i];
                    ans = Math.max(ans, cur);
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}