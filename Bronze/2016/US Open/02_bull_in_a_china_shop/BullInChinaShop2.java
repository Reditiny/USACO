import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


/**
 * @author Red
 * @version 1.0
 */
public class BullInChinaShop2 {
    public static boolean pieces[][][];
    public static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("bcs.in"));
        PrintWriter pw = new PrintWriter("bcs.out");
        StringTokenizer line = new StringTokenizer(r.readLine());
        n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        // 记录所有块 其中 pieces[0] 为完整的块  pieces[1]-pieces[k]为 k 个碎块
        pieces = new boolean[k + 1][n][n];
        // 记录所有块边缘 排除额外 '.' 的干扰
        int[][] s = new int[k + 1][n];
        for (int row = 0; row <= k; row++) {
            int right = n - 1;
            int left = 0;
            int bottom = n - 1;
            int top = 0;
            for (int col = 0; col < n; col++) {
                String str = r.readLine();
                for (int l = 0; l < n; l++) {
                    char c = str.charAt(l);
                    pieces[row][col][l] = (c == '#');
                    if (pieces[row][col][l]) {
                        top = Math.max(top, col);
                        bottom = Math.min(bottom, col);
                        left = Math.max(left, l);
                        right = Math.min(right, l);
                    }
                }
            }
            s[row] = new int[]{right, left, bottom, top};
        }

        // 遍历所有两两组合 第 i 个碎块和第 j 个碎块进行拼接
        for (int i = 1; i <= k; i++) {
            for (int j = i + 1; j <= k; j++) {
                // 遍历两个碎块所有可能的拼接方式
                // i 块的下边界紧贴原块的上边移动到 i 块的上边界紧贴原块的下边
                // 此处对遍历过程有优化将原本块的边界进行缩减，去除边界上填充的 '.'
                //
                //  ------------  0
                //  |          |
                //  |   ----   |  top
                //  |   |  |   |
                //  |   ----   |  bottom
                //  |          |
                //  ------------  n-1
                // 向下移动 (n-1) - s[i][3](top) 步时top边紧贴n-1边
                // 向上移动 s[i][2](bottom) 步时bottom边紧贴0边
                for (int iDetalRow = s[i][3] - n + 1; iDetalRow <= s[i][2]; iDetalRow++) {
                    for (int iDetalCol = s[i][1] - n + 1; iDetalCol <= s[i][0]; iDetalCol++) {
                        // j 块同理
                        for (int jDetalRow = s[j][3] - n + 1; jDetalRow <= s[j][2]; jDetalRow++) {
                            for (int jDetalCol = s[j][1] - n + 1; jDetalCol <= s[j][0]; jDetalCol++) {
                                // 对于当前组合查看是否与原块一致
                                boolean good = true;
                                for (int x = 0; x < n; x++) {
                                    for (int y = 0; y < n; y++) {
                                        // (curRow,curColumn)为原块的坐标
                                        // (curRow + iDeltaRow,curColumn + iDeltaColumn)为 i 块上对应(curRow,curColumn)的坐标
                                        boolean iPiece = check(i, x + iDetalRow, y + iDetalCol);
                                        // (curRow + jDeltaRow,curColumn + jDeltaColumn)为 j 块上对应(curRow,curColumn)的坐标
                                        boolean jPiece = check(j, x + jDetalRow, y + jDetalCol);
                                        // 两块对应位置同时为 '#' 说明无法这样拼接
                                        if (iPiece && jPiece) {
                                            good = false;
                                            break;
                                        }
                                        // 是否与原块一致
                                        if (pieces[0][x][y] != (iPiece || jPiece)) {
                                            good = false;
                                            break;
                                        }
                                    }
                                    if (!good) {
                                        break;
                                    }
                                }
                                if (good) {
                                    pw.println(i + " " + j);
                                }
                            }
                        }
                    }
                }
            }
        }
        pw.close();
    }

    /**
     * 检查当前块是否为 '#'
     */
    static boolean check(int piece, int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n && pieces[piece][x][y];
    }
}
