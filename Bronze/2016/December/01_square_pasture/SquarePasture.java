import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class SquarePasture {
    static int[][] rectangles = new int[2][4];

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("square.in"));
        PrintWriter pw = new PrintWriter("square.out");
        for (int i = 0; i < 2; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            for (int j = 0; j < 4; j++) {
                rectangles[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 正方形边长为横坐标之差和纵坐标之差的最大值
        int minX = Math.min(rectangles[0][0], rectangles[1][0]);
        int maxX = Math.max(rectangles[0][2], rectangles[1][2]);
        int minY = Math.min(rectangles[0][1], rectangles[1][1]);
        int maxY = Math.max(rectangles[0][3], rectangles[1][3]);
        int edge = Math.max(maxX - minX, maxY - minY);
        pw.println(edge * edge);
        pw.close();
    }
}