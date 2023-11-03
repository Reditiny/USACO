import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class BlockGame {
    public static void main(String[] args) throws Exception {
        // 用数组来存储每个单词中每个字母出现的次数
        int[] letterCount1 = new int[26];   // 记录正面单词
        int[] letterCount2 = new int[26];   // 记录反面单词
        int[] letterCountAns = new int[26]; // 记录最终字母数
        BufferedReader r = new BufferedReader(new FileReader("blocks.in"));
        PrintWriter pw = new PrintWriter("blocks.out");
        int n = Integer.parseInt(r.readLine());
        for (int i = 0; i < n; i++) {
            // 每块板子正反两个单词 记录每块板子每个字符出现的最大次数
            String[] word = r.readLine().split(" ");
            for (int j = 0; j < word[0].length(); j++) {
                letterCount1[word[0].charAt(j) - 'a']++;
            }
            for (int j = 0; j < word[1].length(); j++) {
                letterCount2[word[1].charAt(j) - 'a']++;
            }
            for (int j = 0; j < 26; j++) {
                letterCountAns[j] += Math.max(letterCount1[j], letterCount2[j]);
                letterCount1[j] = 0;
                letterCount2[j] = 0;
            }
        }

        for (int i = 0; i < 26; i++) {
            pw.println(letterCountAns[i]);
        }
        pw.close();
    }
}
