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
public class FeedingTheCows {
    static int T, N, K;
    static char[] cows;
    static char[] ans;
    static int patchCount;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        T = Integer.parseInt(r.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            cows = r.readLine().toCharArray();
            int lastGuernseyPatch = -K - 1;
            int lastHolsteinPatch = -K - 1;
            ans = new char[N];
            for (int j = 0; j < N; j++) {
                ans[j] = '.';
            }
            patchCount = 0;
            // 对于奶牛 j - K，如果它需要牧场，就将牧场放在 j 处
            // 这样不仅满足了 j - K，还最大程度地覆盖了后面的奶牛
            for (int j = K; j < N; j++) {
                if (cows[j - K] == 'G') {
                    if ((j - K) - lastGuernseyPatch > K) {
                        patchCount++;
                        ans[j] = 'G';
                        lastGuernseyPatch = j;
                    }
                } else {
                    if ((j - K) - lastHolsteinPatch > K) {
                        patchCount++;
                        ans[j] = 'H';
                        lastHolsteinPatch = j;
                    }
                }
            }
            // 上述遍历结束后 [0, N - K - 1] 的奶牛都已经被覆盖了
            // [N - K, N - 1] 的奶牛可能还没有被覆盖
            // 从后往前遍历，如果某头奶牛需要牧场，就将牧场放在它前面的第一个空地上
            boolean gNeeds = false;
            for (int j = N - 1; j >= 0; j--) {
                if (cows[j] == 'G' && j - lastGuernseyPatch > K) {
                    gNeeds = true;
                }
            }
            if (gNeeds) {
                for (int j = N - 1; j >= 0; j--) {
                    if (ans[j] == '.') {
                        patchCount++;
                        ans[j] = 'G';
                        break;
                    }
                }
            }
            boolean hNeeds = false;
            for (int j = N - 1; j >= 0; j--) {
                if (cows[j] == 'H' && j - lastHolsteinPatch > K) {
                    hNeeds = true;
                }
            }
            if (hNeeds) {
                for (int j = N - 1; j >= 0; j--) {
                    if (ans[j] == '.') {
                        patchCount++;
                        ans[j] = 'H';
                        break;
                    }
                }
            }
            pw.println(patchCount);
            pw.println(ans);
        }
        pw.close();
    }
}