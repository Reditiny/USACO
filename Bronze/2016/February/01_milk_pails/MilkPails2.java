import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class MilkPails2 {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("pails.in"));
        PrintWriter pw = new PrintWriter("pails.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int maxX = m / x;
        // int maxY = m / y;
        int ans = 0;
        // 搜索范围内遍历得到最大值
        for (int i = 0; i <= maxX; i++) {
            // 对于给定的 i 个 x 确定 y 的数量为 (m - x * i) / y
            int j = (m - x * i) / y;
            ans = Math.max(ans, x * i + y * j);
        }
        pw.println(ans);
        pw.close();
    }
}
