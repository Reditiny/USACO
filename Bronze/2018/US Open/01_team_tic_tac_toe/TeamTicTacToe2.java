import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Red
 * @version 1.0
 */
public class TeamTicTacToe2 {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("tttt.in"));
        PrintWriter pw = new PrintWriter("tttt.out");
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            board[i] = r.readLine().toCharArray();
        }
        HashSet<Character> singleWin = new HashSet<>();
        HashSet<String> teamWin = new HashSet<>();
        char[][] chars = getAllChars(board);
        for (int i = 0; i < 8; i++) {
            putWin(getWin(chars[i]), singleWin, teamWin);
        }
        pw.println(singleWin.size());
        pw.println(teamWin.size());
        pw.close();
    }

    /**
     * 获得棋盘上的8种组合
     */
    static char[][] getAllChars(char[][] board) {
        char[][] targets = new char[8][3];
        int count = 0;
        for (int i = 0; i < 3; i++) {
            targets[count++] = new char[]{board[i][0], board[i][1], board[i][2]};
            targets[count++] = new char[]{board[0][i], board[1][i], board[2][i]};
        }
        targets[count++] = new char[]{board[0][0], board[1][1], board[2][2]};
        targets[count] = new char[]{board[2][0], board[1][1], board[0][2]};
        return targets;
    }

    /**
     * 判断获胜者
     */
    static char[] getWin(char[] c) {
        if (c[1] == c[2] && c[3] == c[1]) {
            return new char[]{c[1]};
        }
        if (c[1] == c[2] || c[1] == c[3] || c[2] == c[3]) {
            return new char[]{(char) Math.min(Math.min(c[1], c[2]), c[3]), (char) Math.max(Math.max(c[1], c[2]), c[3])};
        }
        return new char[]{};
    }

    /**
     * 获胜者放入对应集合
     */
    static void putWin(char[] winChar, HashSet<Character> singleWin, HashSet<String> teamWin) {
        if (winChar.length == 1) {
            singleWin.add(winChar[0]);
        } else if (winChar.length == 2) {
            teamWin.add(String.valueOf(winChar));
        }
    }
}
