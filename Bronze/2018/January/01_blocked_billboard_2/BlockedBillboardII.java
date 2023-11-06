import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class BlockedBillboardII {
    static int[][] billboards = new int[2][4];

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("billboard.in"));
        PrintWriter pw = new PrintWriter("billboard.out");
        for (int i = 0; i < 2; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            for (int j = 0; j < 4; j++) {
                billboards[i][j] = Integer.parseInt(st.nextToken()) + 1000;
            }
        }
        // 判断覆盖了几个角
        int coverCornerCount = 0;
        if (coverCorner(billboards[0][0], billboards[0][1])) {
            coverCornerCount++;
        }
        if (coverCorner(billboards[0][0], billboards[0][3])) {
            coverCornerCount++;
        }
        if (coverCorner(billboards[0][2], billboards[0][1])) {
            coverCornerCount++;
        }
        if (coverCorner(billboards[0][2], billboards[0][3])) {
            coverCornerCount++;
        }
        // 如果没能盖住两个角就需要用和原面积一样大的布
        int ans = area(billboards[0]);
        if (coverCornerCount >= 2) {
            ans -= coverArea(billboards[0], billboards[1]);
        }
        pw.println(ans);
        pw.close();
    }

    /**
     * 判断点是否在矩形内
     */
    static boolean coverCorner(int x, int y) {
        return x >= billboards[1][0] && x <= billboards[1][2] && y >= billboards[1][1] && y <= billboards[1][3];
    }

    /**
     * 计算矩形面积
     */
    static int area(int[] board) {
        return (board[2] - board[0]) * (board[3] - board[1]);
    }

    /**
     * 计算两个矩形重叠的面积
     */
    static int coverArea(int[] board1, int[] board2) {
        int xOverlap = Math.max(0, Math.min(board1[2], board2[2]) - Math.max(board1[0], board2[0]));
        int yOverlap = Math.max(0, Math.min(board1[3], board2[3]) - Math.max(board1[1], board2[1]));
        return xOverlap * yOverlap;
    }
}