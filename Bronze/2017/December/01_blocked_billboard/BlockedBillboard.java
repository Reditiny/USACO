import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class BlockedBillboard {
    static int[][] rectangles = new int[3][4];

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("billboard.in"));
        PrintWriter pw = new PrintWriter("billboard.out");
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            for (int j = 0; j < 4; j++) {
                rectangles[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // rectangles[0] 表示第一个广告牌的坐标 rectangles[1] 表示第二个广告牌的坐标 rectangles[2] 表示卡车的坐标
        // 计算广告牌面积和卡车挡住两个广告牌的面积
        int ans = 0;
        for (int i = 0; i < 2; i++) {
            ans += area(rectangles[i]) - coverArea(rectangles[i], rectangles[2]);
        }
        pw.println(ans);
        pw.close();
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
    static int coverArea(int[] board, int[] truck) {
        int xOverlap = Math.max(0, Math.min(board[2], truck[2]) - Math.max(board[0], truck[0]));
        int yOverlap = Math.max(0, Math.min(board[3], truck[3]) - Math.max(board[1], truck[1]));
        return xOverlap * yOverlap;
    }

}