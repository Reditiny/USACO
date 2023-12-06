import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class MazeTacToe {
    static int n;
    static char[][][] maze;
    // 一二维表示坐标，三维表示棋盘状态，棋盘有9个格子，每个格子有三种状态，所以一共有3^9种状态
    // 每个格子有不同的权重可以使得每个棋盘状态对应唯一的一个数字
    //[ 3^0, 3^1, 3^2
    //  3^3, 3^4, 3^5
    //  3^6, 3^7, 3^8 ]
    static boolean[][][] visited;
    // 3的幂的数组 3^0 = 1, 3^1 = 3, 3^2 = 9, 3^3 = 27, 3^4 = 81, 3^5 = 243, 3^6 = 729, 3^7 = 2187, 3^8 = 6561
    static int[] pow3 = new int[10];
    static int startI = 0, startJ = 0;
    static int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static HashSet<Integer> answers = new HashSet<Integer>();
    // 用于判断胜利的棋盘状态 OOM 或 MOO
    static int[][] winning = {{2,2,1},{1,2,2}};

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        n = Integer.parseInt(st.nextToken());
        maze = new char[n][n][3];
        visited = new boolean[n][n][(int) Math.pow(3, 9)];
        for (int i = 0; i < n; i++) {
            String str = r.readLine();
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    maze[i][j][k] = str.charAt(j * 3 + k);
                }
                if (maze[i][j][0] == 'B') {
                    startI = i;
                    startJ = j;
                }
            }
        }
        pow3[0] = 1;
        for (int i = 1; i < 10; i++) {
            pow3[i] = pow3[i - 1] * 3;
        }
        dfs(startI, startJ, 0);
        pw.println(answers.size());
        pw.close();
    }

    /**
     * 深度优先搜索
     */
    static void dfs(int i, int j, int b) {
        if (visited[i][j][b]) {
            return;
        }
        visited[i][j][b] = true;
        // 如果当前格子是 M 或者 O 说明可能改变棋盘状态 b
        if (maze[i][j][0] == 'M' || maze[i][j][0] == 'O') {
            // 棋盘的位置(row,col)
            int row = maze[i][j][1] - '1';
            int col = maze[i][j][2] - '1';
            // 棋盘位置对应的索引，用于确定权重
            int idx = row * 3 + col;
            int currentChar = (b / pow3[idx]) % 3;
            if (currentChar == 0) {
                int newChar = maze[i][j][0] == 'M' ? 1 : 2;
                // 原本位置上是格子，现在放入棋子 M 或 O
                // 因为只改变这一格上的状态，所以只需要加上这一格的权重即可
                b += newChar * pow3[idx];
                if (!visited[i][j][b] && testWin(b)) {
                    answers.add(b);
                    return;
                }
                visited[i][j][b] = true;
            }
        }
        // 不改变状态继续遍历
        for (int k = 0; k < 4; k++) {
            int nextI = i + moves[k][0], nextJ = j + moves[k][1];
            if (nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= n || maze[nextI][nextJ][0] == '#') {
                continue;
            }
            dfs(nextI, nextJ, b);
        }
    }

    /**
     * 判断棋盘状态 b 是否胜利
     * 先要把状态 b 转换为棋盘
     * 1.每个格子有不同的权重
     * [ 3^0, 3^1, 3^2
     *   3^3, 3^4, 3^5
     *   3^6, 3^7, 3^8 ]
     * 2.不同的字符有不同的分数 空，M，O 分别为 0，1，2
     * 这样每个棋盘都对于唯一的一个数字
     * 棋盘                     状态
     * [ _, O, M        3^0 * 0 + 3^1 * 2 + 3^2 * 1 +
     *   M, M, _    =   3^3 * 1 + 3^4 * 1 + 3^5 * 0 +
     *   O, _, _ ]      3^6 * 2 + 3^7 * 0 + 3^8 * 0 = 609
     */
    static boolean testWin(int b) {
        // 将状态 b 转换为棋盘
        int[][] cells = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = b % 3;
                b /= 3;
            }
        }
        // 判断是否胜利
        // 行是否满足 211 或 112
        for (int r = 0; r < 3; r++) {
            for (int w = 0; w < 2; w++) {
                if (cells[r][0] == winning[w][0] && cells[r][1] == winning[w][1] && cells[r][2] == winning[w][2]) {
                    return true;
                }
            }
        }
        // 列是否满足 211 或 112
        for (int c = 0; c < 3; c++) {
            for (int w = 0; w < 2; w++) {
                if (cells[0][c] == winning[w][0] && cells[1][c] == winning[w][1] && cells[2][c] == winning[w][2]) {
                    return true;
                }
            }
        }
        // 对角线是否满足 211 或 112
        for (int w = 0; w < 2; w++) {
            if (cells[0][0] == winning[w][0] && cells[1][1] == winning[w][1] && cells[2][2] == winning[w][2]) {
                return true;
            }
            if (cells[0][2] == winning[w][0] && cells[1][1] == winning[w][1] && cells[2][0] == winning[w][2]) {
                return true;
            }
        }
        return false;
    }

}