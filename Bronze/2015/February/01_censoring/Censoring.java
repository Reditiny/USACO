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
        // 此处使用StringBuilder而不是String，因为String的+操作会生成新的String对象而不是简单地在后面追加，所以效率较低
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < text.length; i++) {
            ans.append(text[i]);
            if (ans.length() > target.length() && ans.substring(ans.length() - target.length(), ans.length()).equals(target)) {
                ans.delete(ans.length() - target.length(), ans.length());
            }
        }
        pw.println(ans);
        pw.close();
    }
}
