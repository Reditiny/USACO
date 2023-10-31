import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class ModernArt {
    static final int MAX_COLOR = 9;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("art.in"));
        PrintWriter pw = new PrintWriter("art.out");
        int n = Integer.parseInt(r.readLine());
        // 记录每个颜色的矩形范围 为省去后面赋值时 index - 1 的麻烦 index 从 1 开始
        int[] left = new int[MAX_COLOR + 1];
        int[] right = new int[MAX_COLOR + 1];
        int[] down = new int[MAX_COLOR + 1];
        int[] up = new int[MAX_COLOR + 1];
        for (int c = 1; c <= MAX_COLOR; c++) {
            left[c] = up[c] = Integer.MAX_VALUE;
            right[c] = down[c] = -1;
        }
        boolean[] validStart = new boolean[MAX_COLOR + 1];
        int[][] grid = new int[n][n];
        for (int row = 0; row < n; row++) {
            String line = r.readLine();
            for (int col = 0; col < n; col++) {
                int curColor = Character.getNumericValue(line.charAt(col));
                grid[row][col] = curColor;
                if (curColor != 0) {
                    left[curColor] = Math.min(left[curColor], col);
                    right[curColor] = Math.max(right[curColor], col);
                    down[curColor] = Math.max(down[curColor], row);
                    up[curColor] = Math.min(up[curColor], row);
                    validStart[curColor] = true;
                }
            }
        }
        // 检查每个 color 的矩形范围
        for (int color = 1; color <= MAX_COLOR; color++) {
            for (int row = up[color]; row <= down[color]; row++) {
                for (int col = left[color]; col <= right[color]; col++) {
                    // 若 color 边界内的格子不是 color 颜色则该格子的颜色一定不是起始颜色(覆盖了当前color)
                    if (grid[row][col] != color) {
                        validStart[grid[row][col]] = false;
                    }
                }
            }
        }

        int validStartCount = 0;
        for (boolean ok : validStart) {
            if (ok) {
                validStartCount++;
            }
        }
        pw.print(validStartCount);
        pw.close();
    }
}
