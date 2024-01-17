import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class BlockedBillboard2 {
    static int MAX_POS = 2000;
    static boolean[][] visibility = new boolean[MAX_POS][MAX_POS];

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("billboard.in"));
        PrintWriter pw = new PrintWriter("billboard.out");
        for (int i = 0; i < 3; ++i) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            int x1 = Integer.parseInt(st.nextToken()) + MAX_POS / 2;
            int y1 = Integer.parseInt(st.nextToken()) + MAX_POS / 2;
            int x2 = Integer.parseInt(st.nextToken()) + MAX_POS / 2;
            int y2 = Integer.parseInt(st.nextToken()) + MAX_POS / 2;

            for (int x = x1; x < x2; ++x) {
                for (int y = y1; y < y2; ++y) {
                    visibility[x][y] = i < 2;
                }
            }
        }

        int area = 0;
        for (int i = 0; i < MAX_POS; ++i) {
            for (int j = 0; j < MAX_POS; ++j) {
                if (visibility[i][j]) {
                    area++;
                }
            }
        }

        pw.println(area);
        pw.close();
    }

}