import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;

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
        for (int i = 0; i < 3; i++) {
            char[] winChar = getWin(board[i][0], board[i][1], board[i][2]);
            putWin(winChar, singleWin, teamWin);
            winChar = getWin(board[0][i], board[1][i], board[2][i]);
            putWin(winChar, singleWin, teamWin);
        }
        char[] winChar = getWin(board[0][0], board[1][1], board[2][2]);
        putWin(winChar, singleWin, teamWin);
        winChar = getWin(board[0][2], board[1][1], board[2][0]);
        putWin(winChar, singleWin, teamWin);
        pw.println(singleWin.size());
        pw.println(teamWin.size());
        pw.close();
    }

    /**
     * 判断获胜者
     */
    static char[] getWin(char c1, char c2, char c3) {
        if (c1 == c2 && c3 == c1){
            return new char[]{c1};
        }
        if (c1 == c2 || c1 == c3 || c2 == c3) {
           return new char[]{getMin(c1, c2, c3), getMax(c1, c2, c3)};
        }
        return new char[]{};
    }

    static void putWin(char[] winChar, HashSet<Character> singleWin, HashSet<String> teamWin){
        if (winChar.length == 1) {
            singleWin.add(winChar[0]);
        } else if (winChar.length == 2) {
            teamWin.add(String.valueOf(winChar));
        }
    }

    static char getMin(char c1, char c2, char c3){
        if (c1 <= c2 && c1 <= c3){
            return c1;
        }
        if (c2 <= c1 && c2 <= c3){
            return c2;
        }
        return c3;
    }

    static char getMax(char c1, char c2, char c3){
        if (c1 >= c2 && c1 >= c3){
            return c1;
        }
        if (c2 >= c1 && c2 >= c3){
            return c2;
        }
        return c3;
    }

}
