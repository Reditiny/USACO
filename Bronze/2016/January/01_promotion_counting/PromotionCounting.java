import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class PromotionCounting {
    // 一维 bronze silver gold platinum
    // 二维 before after
    static int[][] participants = new int[4][2];
    static int[] ans = new int[3];

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("promote.in"));
        PrintWriter pw = new PrintWriter("promote.out");
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            for (int j = 0; j < 2; j++) {
                participants[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 高级别多出来的人数就是低级别晋级的人
        for (int i = 3; i > 0; i--) {
            int delta = participants[i][1] - participants[i][0];
            participants[i - 1][1] += delta;
            ans[i - 1] += delta;
        }
        for (int i = 0; i < 3; i++) {
            pw.println(ans[i]);
        }
        pw.close();
    }
}