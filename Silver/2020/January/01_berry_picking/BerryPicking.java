import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class BerryPicking {
    static int N, K;
    static int[] berries;
    static int maxBerry;
    static int ans = -1;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("berries.in"));
        PrintWriter pw = new PrintWriter("berries.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        berries = new int[N];
        st = new StringTokenizer(r.readLine());
        for (int i = 0; i < N; i++){
            berries[i] = Integer.parseInt(st.nextToken());
            maxBerry = Math.max(maxBerry, berries[i]);
        }
        // 因为要给出去多的那一半，所有每个篮子尽可能放同样多即可
        // 每个篮子里最少放一个浆果，最多放maxBerry个浆果
        for (int i = 1; i <= maxBerry; i++) {
            int count = 0;
            int[] leftOver = new int[N];
            for (int j = 0; j < N; j++) {
                count += berries[j] / i;
                leftOver[j] = berries[j] % i;
            }
            // 上述遍历完成后篮子里的浆果只可能是 i 个或 0 个
            if (count >= K) {
                // 篮子全都能装到 i 个
                ans = Math.max(ans, K / 2 * i);
            } else if (count >= K / 2) {
                // 一半以上的篮子能装到，一半拿给 Elsie
                // 剩下的空篮子可以再装采摘之后剩下的浆果
                int berries = 0;
                berries += (count - K / 2) * i;
                Arrays.sort(leftOver);
                int ix = leftOver.length - 1;
                for (int j = count - K / 2; j < K / 2; j++) {
                    if (ix < 0) {
                        continue;
                    }
                    berries += leftOver[ix--];
                }
                ans = Math.max(ans, berries);
            }
        }
        pw.println(ans);
        pw.close();
    }
}