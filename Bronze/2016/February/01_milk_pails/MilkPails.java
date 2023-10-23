import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class MilkPails {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("pails.in"));
        PrintWriter pw = new PrintWriter("pails.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int maxX = m / x;
        int maxY = m / y;
        int ans = 0;
        // 搜索范围内遍历得到最大值
        for (int i = 0; i <= maxX; i++) {
            for (int j = 0; j <= maxY; j++) {
                if (x * i + y * j > m) {
                    break;
                }
                if (x * i + y * j > ans) {
                    ans = x * i + y * j;
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}
