import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class WhyDidTheCowCrossTheRoadII2 {
    static Stack<Character> stack = new Stack<>();
    static int[] visited = new int[26];
    static int ans = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("circlecross.in"));
        PrintWriter pw = new PrintWriter("circlecross.out");

        String s = r.readLine();

        for (int i = 0; i < 52; i++) {
            char ch = s.charAt(i);
            int chIndex = ch - 'A';
            if (visited[chIndex] == 0) {
                visited[chIndex] = 1;
                stack.push(ch);
            } else {
                int startPos = 0;
                while (startPos < stack.size()) {
                    if (stack.get(startPos) == ch) {
                        break;
                    }
                    startPos++;
                }
                ans += stack.size() - startPos - 1;
                stack.remove(startPos);
            }
        }

        pw.println(ans);
        pw.close();
    }
}
