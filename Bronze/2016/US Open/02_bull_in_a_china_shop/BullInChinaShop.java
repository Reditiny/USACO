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
        // 记录所有块 其中 pieces[0] 为完整的块
        pieces = new boolean[11][8][8];
        // 记录所有块边缘 排除额外 '.' 的干扰
        int[][] s = new int[11][4];
        n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        for (int i = 0; i <= k; i++) {
            int left = n - 1;
            int right = 0;
            int top = n - 1;
            int bottom = 0;
            for (int j = 0; j < n; j++) {
                String str = r.readLine();
                for (int l = 0; l < n; l++) {
                    char c = str.charAt(l);
                    pieces[i][j][l] = (c == '#');
                    if (pieces[i][j][l]) {
                        bottom = Math.max(bottom, j);
                        top = Math.min(top, j);
                        right = Math.max(right, l);
                        left = Math.min(left, l);
                    }
                }
            }
            s[i] = new int[]{left, right, top, bottom};
        }

        // 遍历所有两两组合
        for (int i = 1; i <= k; i++) {
            for (int j = i + 1; j <= k; j++) {
                // 从上到下 从左到右 遍历所有重叠方式
                for (int idx = s[i][3] - n + 1; idx <= s[i][2]; idx++) {
                    for (int idy = s[i][1] - n + 1; idy <= s[i][0]; idy++) {
                        for (int jdx = s[j][3] - n + 1; jdx <= s[j][2]; jdx++) {
                            for (int jdy = s[j][1] - n + 1; jdy <= s[j][0]; jdy++) {
                                boolean good = true;
                                for (int x = 0; x < n; x++) {
                                    for (int y = 0; y < n; y++) {
                                        boolean iPiece = check(i, x + idx, y + idy);
                                        boolean jPiece = check(j, x + jdx, y + jdy);
                                        // 两块上不能同时为 '#'
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
