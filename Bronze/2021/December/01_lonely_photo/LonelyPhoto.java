import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class LonelyPhoto {
    static int N;
    static char[] breeds;
    static int[] breedCount = new int[2];
    static long ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        N = Integer.parseInt(r.readLine());
        breeds = r.readLine().toCharArray();
        // 以当前字符作为孤立牛，计算左右两边紧挨着有多少个不同的字符
        // 0123456789
        // GHGGGHGGHG
        // 例如上面 5 号位的 H
        // 左边有 3 个连续的 G，右边有 2 个连续的 G
        // 当左边两边各至少一个 G 或 左边有 2 个 G 或 右边有 2 个 G 时，这些照片都应该丢弃
        for (int i = 0; i < N; i++) {
            long leftDiffCount = 0;
            for (int k = i - 1; k >= 0 ; k--) {
                if (breeds[k] != breeds[i]) {
                    leftDiffCount++;
                }else{
                    break;
                }
            }
            long rightDiffCount = 0;
            for (int k = i + 1; k < N ; k++) {
                if (breeds[k] != breeds[i]) {
                    rightDiffCount++;
                }else{
                    break;
                }
            }
            ans += leftDiffCount * rightDiffCount + Math.max(leftDiffCount - 1, 0) + Math.max(rightDiffCount - 1, 0);
        }
        pw.println(ans);
        pw.close();
    }
}