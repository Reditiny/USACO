import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class Teleportation {
    static int a, b, x, y;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("teleport.in"));
        PrintWriter pw = new PrintWriter("teleport.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        // 三种情况
        // 1.不使用传送门
        int ans = Math.abs(a - b);
        // 2.使用传送门 x -> y
        ans = Math.min(ans, Math.abs(a - x) + Math.abs(b - y));
        // 3.使用传送门 y -> x
        ans = Math.min(ans, Math.abs(a - y) + Math.abs(b - x));
        pw.println(ans);
        pw.close();
    }
}