import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class BucketBrigade {
    static char[][] farm = new char[10][10];
    static int Lx, Ly, Bx, By, Rx, Ry;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("buckets.in"));
        PrintWriter pw = new PrintWriter("buckets.out");
        for (int i = 0; i < 10; i++) {
            String line = r.readLine();
            for (int j = 0; j < 10; j++) {
                char c = line.charAt(j);
                farm[i][j] = c;
                if (c == 'B') {
                    Bx = i;
                    By = j;
                }
                if (c == 'L') {
                    Lx = i;
                    Ly = j;
                }
                if (c == 'R') {
                    Rx = i;
                    Ry = j;
                }
            }
        }
        // 当 R 挡在 B 和 L 之间时，需要多走两步绕开
        // L12345B   L12R67B
        //             345
        // 其他情况就是 LB 水平和数值距离只和
        int deltaX = Math.abs(Lx - Bx);
        int deltaY = Math.abs(Ly - By);
        // 由于是计算间隔所以是距离-1
        int ans = deltaY + deltaX - 1;
        if (((Lx == Bx && Bx == Rx) && ((Ry - By) * (Ry - Ly) < 0))
                || ((Ly == By && By == Ry) && ((Rx - Bx) * (Rx - Lx) < 0))) {
            ans += 2;
        }
        pw.println(ans);
        pw.close();
    }
}