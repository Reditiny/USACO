import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Red
 * @version 1.0
 */
public class Censoring {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("censor.in"));
        PrintWriter pw = new PrintWriter("censor.out");
        char[] text = r.readLine().toCharArray();
        String target = r.readLine();
        // 将文本中字符依次放入ans中，每次都判断ans是否以target结尾
        List<Character> ans = new ArrayList<Character>();
        for (int i = 0; i < text.length; i++) {
            ans.add(text[i]);
            if (endWith(ans, target)) {
                for (int j = 0; j < target.length(); j++) {
                    ans.remove(ans.size() - 1);
                }
            }
        }
        pw.println(ans.toString().replaceAll("[\\[\\]\\,\\s]", ""));
        pw.close();
    }

    /**
     * 判断ans中是否以target结尾
     */
    public static boolean endWith(List<Character> ans, String target) {
        if (ans.size() < target.length()) {
            return false;
        }
        for (int i = 0; i < target.length(); i++) {
            if (ans.get(ans.size() - target.length() + i) != target.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
