import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author Red
 * @version 1.0
 */
public class SleepyCowHerding {
    static int N;
    static int[] cows;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("herding.in"));
        PrintWriter pw = new PrintWriter("herding.out");
        N = Integer.parseInt(r.readLine());
        cows = new int[N];
        for (int i = 0; i < N; i++) {
            cows[i] = Integer.parseInt(r.readLine());
        }
        Arrays.sort(cows);

        int minMoves = Integer.MAX_VALUE;
        if (cows[N - 2] - cows[0] == N - 2 && cows[N - 1] - cows[N - 2] > 2) {
            // ABCD__E 两步完成 -> BCD_AE -> BCDEA
            minMoves = 2;
        } else if (cows[N - 1] - cows[1] == N - 2 && cows[1] - cows[0] > 2) {
            // A__BCDE 两步完成 -> AE_BCD -> EABCD
            minMoves = 2;
        } else {
            // A__B_CDE 找到距离为 N 的两头牛，每次移动占据两牛之间的一个空位
            // 移动次数为中间的空位数 -> A__BECD -> A_DBEC -> ACDBE
            int farthestCow = 0;
            for (int currCow = 0; currCow < N; currCow++) {
                while (farthestCow + 1 < N && cows[farthestCow + 1] - cows[currCow] < N) {
                    farthestCow++;
                }
                minMoves = Math.min(minMoves, N - (farthestCow - currCow + 1));
            }
        }
        // 记录空位总数
        int gapNum = 0;
        for (int i = 1; i < N; i++) { gapNum += cows[i] - cows[i - 1] - 1; }
        // 最大步数为总间隙减去第一个和最后一个间隙中的较小值
        // 即除去首尾间隙的最小值，其他空位全部走一遍
        // A___B_C_D__E -> A___B_CED -> A___BDCE -> A__EBDC -> A_CEBD -> ADCEB
        int maxMoves = gapNum - Math.min((cows[1] - cows[0] - 1),(cows[N - 1] - cows[N - 2] - 1));
        pw.println(minMoves);
        pw.println(maxMoves);
        pw.close();
    }
}