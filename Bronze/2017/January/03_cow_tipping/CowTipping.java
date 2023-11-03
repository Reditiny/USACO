import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * @author Red
 * @version 1.0
 */
public class CowTipping {
    static boolean[][] cowTipped;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("cowtip.in"));
        PrintWriter pw = new PrintWriter("cowtip.out");
        int n = Integer.parseInt(r.readLine());
        cowTipped = new boolean[n][n];
        for (int row = 0; row < n; row++) {
            String line = r.readLine();
            for (int col = 0; col < n; col++) {
                cowTipped[row][col] = line.charAt(col) != '0';
            }
        }
        // 从右下角开始先row方向遍历再col方向遍历查看(row,col)位置是否需要翻转
        // 当遍历完(row,col)之后，该位置的牛不会再受后续翻转的影响
        int minFlips = 0;
        for (int row = n - 1; row >= 0; row--) {
            for (int col = n - 1; col >= 0; col--) {
                minFlips += flip(row, col) ? 1 : 0;
            }
        }
        pw.println(minFlips);
        pw.close();
    }

    /**
     * 若(row, col)处的牛不正常则翻转左上角到(row, col)范围内的牛
     * 返回值是否进行了翻转
     */
    static boolean flip(int row, int col) {
        if (cowTipped[row][col]) {
            for (int r = 0; r <= row; r++) {
                for (int c = 0; c <= col; c++) {
                    cowTipped[r][c] = !cowTipped[r][c];
                }
            }
            return true;
        }
        return false;
    }
}
