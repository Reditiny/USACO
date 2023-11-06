import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class WordProcessor {
    static int n, k;
    static String[] words;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("word.in"));
        PrintWriter pw = new PrintWriter("word.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        words = new String[n];
        st = new StringTokenizer(r.readLine());
        for (int i = 0; i < n; i++) {
            words[i] = st.nextToken();
        }
        // 因为每次循环不确定要输出多少个字符，所以最后并没有 i++，而是放到循环体内去改变
        for (int i = 0; i < n; ) {
            int j = i;
            int len = 0;
            // 从 i 开始，尽可能多的输出单词，直到长度超过 k
            while (j < n && len + words[j].length() <= k) {
                len += words[j].length();
                j++;
            }
            for (int l = i; l < j; l++) {
                pw.print(words[l]);
                if (l != j - 1) {
                    pw.print(" ");
                }
            }
            pw.println();
            i = j;
        }
        pw.close();
    }
}