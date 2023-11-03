import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


/**
 * @author Red
 * @version 1.0
 */
public class BullInChinaShop {
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
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                String str = r.readLine();
                for (int l = 0; l < n; l++) {
                    char c = str.charAt(l);
                    pieces[i][j][l] = (c == '#');
                }
            }
        }
        // 遍历所有两两组合 第 i 个碎块和第 j 个碎块进行拼接
        for (int i = 1; i <= k; i++) {
            for (int j = i + 1; j <= k; j++) {
                // 遍历两个碎块所有可能的拼接方式
                // i 块的下边紧贴原块的上边移动到 i 块的上边紧贴原块的下边
                for (int iDeltaRow = -(n - 1); iDeltaRow <= n - 1; iDeltaRow++) {
                    // i 块的右边紧贴原块的左边移动到 i 块的左边紧贴原块的右边
                    for (int iDeltaColumn = -(n - 1); iDeltaColumn <= n - 1; iDeltaColumn++) {
                        // j 块同理
                        for (int jDeltaRow = -(n - 1); jDeltaRow <= n - 1; jDeltaRow++) {
                            for (int jDeltaColumn = -(n - 1); jDeltaColumn <= n - 1; jDeltaColumn++) {
                                // 可能有很多无效组合 但由于原块中有 '.' 填充的空白空间所以不能确定哪个组合是无效的
                                // 对于当前组合查看是否与原块一致
                                boolean good = true;
                                for (int curRow = 0; curRow < n; curRow++) {
                                    for (int curColumn = 0; curColumn < n; curColumn++) {
                                        // (curRow,curColumn)为原块的坐标
                                        // (curRow + iDeltaRow,curColumn + iDeltaColumn)为 i 块上对应(curRow,curColumn)的坐标
                                        boolean iPiece = check(i, curRow + iDeltaRow, curColumn + iDeltaColumn);
                                        // (curRow + jDeltaRow,curColumn + jDeltaColumn)为 j 块上对应(curRow,curColumn)的坐标
                                        boolean jPiece = check(j, curRow + jDeltaRow, curColumn + jDeltaColumn);
                                        // 两块对应位置同时为 '#' 说明无法这样拼接
                                        if (iPiece && jPiece) {
                                            good = false;
                                            break;
                                        }
                                        // 是否与原块一致
                                        if (pieces[0][curRow][curColumn] != (iPiece || jPiece)) {
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
     * 检查当前块指定位置上是否为 '#'
     */
    static boolean check(int piece, int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n && pieces[piece][x][y];
    }
}
