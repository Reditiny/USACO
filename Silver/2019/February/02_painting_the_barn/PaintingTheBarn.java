import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class PaintingTheBarn {
    static int BarnSize = 1000;
    static int[][] barn = new int[BarnSize+1][BarnSize+1];
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("paintbarn.in"));
        PrintWriter pw = new PrintWriter("paintbarn.out");
        StringTokenizer st = new StringTokenizer(r.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(r.readLine());
            int lowerLeftX = Integer.parseInt(st.nextToken());
            int lowerLeftY = Integer.parseInt(st.nextToken());
            int upperRightX = Integer.parseInt(st.nextToken());
            int upperRightY = Integer.parseInt(st.nextToken());
            // 坐标上的数字表示 从 (0,0) 到当前坐标的矩形区域内的涂色次数
            // 通过四个角的涂色次数可以得到矩形区域内的涂色次数
            barn[lowerLeftX][lowerLeftY]++;
            barn[upperRightX][upperRightY]++;
            barn[lowerLeftX][upperRightY]--;
            barn[upperRightX][lowerLeftY]--;
        }

        int ans = 0;
        // 迭代出每个点的涂色次数
        for (int x = BarnSize-1; x >= 0; x--) {
            for (int y = BarnSize-1; y >= 0; y--) {
                barn[x][y] += barn[x + 1][y] + barn[x][y + 1] - barn[x + 1][y + 1];
                if (barn[x][y] == K) { ans++; }
            }
        }
        pw.println(ans);
        pw.close();
    }
}