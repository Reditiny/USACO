import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * @author Red
 * @version 1.0
 */
public class WhyDidTheCowCrossTheRoadII {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("circlecross.in"));
        PrintWriter pw = new PrintWriter("circlecross.out");
        char[] pos = r.readLine().toCharArray();
        int[] inOrder = new int[26];
        int[] outOrder = new int[26];
        int order = 0;
        boolean[] inField = new boolean[26];
        // 记录进出的顺序 进出用同一个顺序记录 因为进出要放在一起比较
        for (int i = 0; i < 52; i++) {
            int cow = pos[i] - 'A';
            if (!inField[cow]) {
                inField[cow] = true;
                inOrder[cow] = order++;
            } else {
                outOrder[cow] = order++;
            }
        }
        int ans = 0;
        // 满足条件的 cows(i,j) 顺序应该为 i..j..i..j 即 i(in)<j(in)<i(out)<j(out)
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (inOrder[i] < inOrder[j] && inOrder[j] < outOrder[i] && outOrder[i] < outOrder[j]) {
                    ans++;
                }
            }
        }

        pw.println(ans);
        pw.close();
    }
}
