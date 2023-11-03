import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class BovineGenomics {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("cownomics.in"));
        PrintWriter pw = new PrintWriter("cownomics.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] spotty = new char[n][m];
        char[][] plain = new char[n][m];
        for (int i = 0; i < n; i++) {
            spotty[i] = r.readLine().toCharArray();
        }
        for (int i = 0; i < n; i++) {
            plain[i] = r.readLine().toCharArray();
        }
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);
        int ans = 0;
        // 枚举每一列两种牛的基因
        for (int i = 0; i < m; i++) {
            boolean[] spottyBool = new boolean[4];
            boolean[] plainBool = new boolean[4];
            for (int j = 0; j < n; j++) {
                spottyBool[map.get(spotty[j][i])] = true;
                plainBool[map.get(plain[j][i])] = true;
            }
            boolean flag = true;
            for (int j = 0; j < 4; j++) {
                if (spottyBool[j] && plainBool[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans++;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
