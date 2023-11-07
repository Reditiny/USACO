import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;

/**
 * @author Red
 * @version 1.0
 */
public class WhereAmI {
    static HashSet<String> mailBox = new HashSet<String>();
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("whereami.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("whereami.out"));
        n = Integer.parseInt(r.readLine());
        String boxes = r.readLine();
        int ans = n;
        boolean ok;
        // 依次截取不同长度的子串，判断是否有重复
        for (int scope = 1; scope <= n; scope++) {
            ok = true;
            for (int i = 0; i <= n - scope; i++) {
                String sub = boxes.substring(i, i + scope);
                if (!mailBox.contains(sub)) {
                    mailBox.add(sub);
                } else {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans = scope;
                break;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
