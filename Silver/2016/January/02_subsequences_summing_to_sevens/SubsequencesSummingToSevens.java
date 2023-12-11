import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author Red
 * @version 1.0
 */
public class SubsequencesSummingToSevens {
    static int N;
    // first[i] 存储首次 前缀和 % 7 == i 时的下标
    static int[] first = new int[7];
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("div7.in"));
        PrintWriter pw = new PrintWriter("div7.out");
        N = Integer.parseInt(r.readLine());

        int ans = 0;
        Arrays.fill(first, -1);
        first[0] = 0;
        int curMod = 0;
        // 如果 前j个的和 % 7 == i 那么当前可以拍的长度就是 j - first[i]
        for (int j = 0; j < N; j++) {
            int cowId = Integer.parseInt(r.readLine());
            curMod = (curMod + cowId) % 7;
            if (first[curMod] == -1) {
                first[curMod] = j;
            } else {
                ans = Math.max(ans, j - first[curMod]);
            }
        }
        pw.println(ans);
        pw.close();
    }
}