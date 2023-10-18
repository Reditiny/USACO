import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class TheCowSignal {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("cowsignal.in"));
        PrintWriter pw = new PrintWriter("cowsignal.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        // 每个字符串 s 的每个字符重复 k 次
        for(int i = 0; i < m; i++) {
            String s = r.readLine();
            for(int j = 0; j < k; j++) {
                for(int l = 0; l < n; l++) {
                    for(int o = 0; o < k; o++) {
                        pw.print(s.charAt(l));
                    }
                }
                pw.println();
            }
        }
        pw.close();
    }
}
