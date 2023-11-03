import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class DaisyChains {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(r.readLine());
        StringTokenizer st = new StringTokenizer(r.readLine());
        int[] daisies = new int[n];
        for (int i = 0; i < n; i++) {
            daisies[i] = Integer.parseInt(st.nextToken());
        }
        int ans = 0;
        // 枚举所有可能的区间
        for (int i = 0; i < n; i++) {
            int sum = 0;
            int count = 0;
            for (int j = i; j < n; j++) {
                sum += daisies[j];
                count++;
                if (sum % count == 0) {
                    int avg = sum / count;
                    for (int k = i; k <= j; k++) {
                        if (daisies[k] == avg) {
                            ans++;
                            break;
                        }
                    }
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}
