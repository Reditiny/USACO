import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class FencePainting {
    static int a, b, c, d;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("paint.in"));
        PrintWriter pw = new PrintWriter("paint.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(r.readLine());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        // 判断两个区间是否有交叉
        if (b < c || d < a) {
            pw.println(b - a + d - c);
        } else {
            pw.println(Math.max(b, d) - Math.min(a, c));
        }
        pw.close();
    }
}