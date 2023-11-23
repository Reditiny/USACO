import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class MooOperations {
    static int Q;
    static int ans;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        Q = Integer.parseInt(r.readLine());
        for(int i = 0; i < Q; i++) {
            String line = r.readLine();
            // 最终只留三个字符，而且这三个字符最开始的位置也一定是连续的
            ans = line.length() - 3;
            if(line.contains("MOO")){
                ans += 0;
            }else if(line.contains("MOM") || line.contains("OOO")) {
                ans += 1;
            }else if(line.contains("OOM")) {
                ans += 2;
            }else {
                ans = -1;
            }
            pw.println(ans);
        }
        pw.close();
    }
}