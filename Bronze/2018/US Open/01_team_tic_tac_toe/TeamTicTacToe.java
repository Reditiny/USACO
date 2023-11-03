import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * @author Red
 * @version 1.0
 */
public class TeamTicTacToe {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("tttt.in"));
        PrintWriter pw = new PrintWriter("tttt.out");
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            board[i] = r.readLine().toCharArray();
        }
        int singleCount = 0;
        int teamCount = 0;
        for (char c1 = 'A'; c1 <= 'Z'; c1++) {
            // 单牛获胜
            if (singleWin(c1, board)) {
                singleCount++;
            }
            for (char c2 = 'Z'; c2 > c1; c2--) {
                // 双牛获胜  teamWin 中已实现 ZA 和 AZ 为相同组合
                if (teamWin(c1, c2, board)) {
                    teamCount++;
                }
            }
        }
        pw.println(singleCount);
        pw.println(teamCount);
        pw.close();
    }

    /**
     * 判断给定单牛是否获胜
     *
     * @param target 目标牛
     * @param board  棋盘
     * @return 是否获胜
     */
    static boolean singleWin(char target, char[][] board) {
        if (board[0][0] == target && board[1][1] == target && board[2][2] == target) {
            return true;
        }
        if (board[0][2] == target && board[1][1] == target && board[2][0] == target) {
            return true;
        }
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == target && board[i][1] == target && board[i][2] == target) {
                return true;
            }
            if (board[0][i] == target && board[1][i] == target && board[2][i] == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断给定双牛是否获胜
     *
     * @param target1 牛1
     * @param target2 牛2
     * @param board   棋盘
     * @return 是否获胜
     */
    static boolean teamWin(char target1, char target2, char[][] board) {
        for (int i = 0; i < 3; i++) {
            if (match2(target1, target2, board[i][0], board[i][1], board[i][2])) {
                return true;
            }
            if (match2(target1, target2, board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        if (match2(target1, target2, board[0][0], board[1][1], board[2][2])) {
            return true;
        }
        if (match2(target1, target2, board[0][2], board[1][1], board[2][0])) {
            return true;
        }
        return false;
    }

    /**
     * 判断给定三个字符是否由两个目标字符组成
     * 即双牛的获胜逻辑
     */
    static boolean match2(char target1, char target2, char c1, char c2, char c3) {
        return (c1 == target1 && c2 == target2 && c3 == target2) ||
                (c1 == target2 && c2 == target1 && c3 == target2) ||
                (c1 == target2 && c2 == target2 && c3 == target1) ||
                (c1 == target2 && c2 == target1 && c3 == target1) ||
                (c1 == target1 && c2 == target2 && c3 == target1) ||
                (c1 == target1 && c2 == target1 && c3 == target2);
    }
}
