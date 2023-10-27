import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class MooLanguage {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int t = Integer.parseInt(r.readLine());
        for (int i = 0; i < t; i++) {
            List<String> nouns = new ArrayList<String>();
            List<String> tverb = new ArrayList<String>();
            List<String> iverb = new ArrayList<String>();
            List<String> conj = new ArrayList<String>();
            StringTokenizer st = new StringTokenizer(r.readLine());
            int n = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(r.readLine());
                String word = st.nextToken();
                String type = st.nextToken();
                switch (type) {
                    case "noun":
                        nouns.add(word);
                        break;
                    case "transitive-verb":
                        tverb.add(word);
                        break;
                    case "intransitive-verb":
                        iverb.add(word);
                        break;
                    case "conjunction":
                        conj.add(word);
                        break;
                    default:
                        break;
                }
            }

            int ans = 0;
            int t1 = 0, t2 = 0, combine = 0, tackEnd = 0;
            // 遍历两种动词出现的个数 type1:intransitive-verb  type2:transitive-verb
            for (int type1 = 0; type1 <= iverb.size(); type1++) {
                int nounCount = nouns.size(), conjCount = conj.size();
                int period = p, comma = c;
                int curWords = 0;
                // type1  noun intransitive-verb
                curWords += 2 * type1;
                nounCount -= type1;
                if (nounCount < 0) {    // 当前 type1 数 noun 数量不足
                    continue;
                }
                // 两个noun和一个transitive-verb组成一个type2句子
                int sentenceLimit = Math.min(tverb.size(), nounCount / 2);
                // 两个type2句子需要一个连词和一个句号  一个type2句子需要一个句号
                int symbolLimit = Math.min(conjCount, period) * 2 + Math.max(0, period - conjCount);
                // type2  noun transitive-verb noun  可以得到的最大数量
                int type2 = Math.min(sentenceLimit, symbolLimit);
                curWords += 3 * type2;
                nounCount -= 2 * type2;
                // conj 最多能连接的句子数
                int total = type1 + type2;
                int canCombine = Math.min(total / 2, conjCount);
                curWords += canCombine;
                period -= total - canCombine;
                if (period < 0) {   // 当前 type1 数 period 数量不足
                    continue;
                }
                // 多余的动词可以用 , noun 接在 type2 后面
                int tack = 0;
                if (type2 > 0) {
                    tack = Math.min(nounCount, comma);
                    curWords += tack;
                }
                if (curWords > ans) {
                    ans = curWords;
                    t1 = type1;
                    t2 = type2;
                    combine = canCombine;
                    tackEnd = tack;
                }
            }
            pw.println(ans);
            if (ans == 0) {
                pw.println();
                continue;
            }
            // 构造句子
            List<List<String>> sentences = new ArrayList<>();
            // type1
            for (int j = 0; j < t1; j++) {
                List<String> sentence = new ArrayList<String>();
                sentence.add(nouns.remove(nouns.size() - 1));
                sentence.add(iverb.remove(iverb.size() - 1));
                sentences.add(sentence);
            }
            // type2
            for (int j = 0; j < t2; j++) {
                List<String> sentence = new ArrayList<String>();
                sentence.add(nouns.remove(nouns.size() - 1));
                sentence.add(tverb.remove(tverb.size() - 1));
                sentence.add(nouns.remove(nouns.size() - 1));
                sentences.add(sentence);
            }
            // 连接所有句子 动词 连词 符号  注意处理结尾处可能多出的空格
            StringBuilder output = new StringBuilder();
            for (int j = 0; j < sentences.size(); j++) {
                for (String k : sentences.get(j)) {
                    output.append(k).append(" ");
                }
                if (j % 2 == 0 && combine > 0) {
                    combine--;
                    output.append(conj.get(conj.size() - 1)).append(" ");
                    conj.remove(conj.size() - 1);
                } else {
                    output.deleteCharAt(output.length() - 1);
                    output.append(". ");
                }
            }
            output.deleteCharAt(output.length() - 1);
            if (tackEnd > 0) {
                output.deleteCharAt(output.length() - 1);
                for (int j = 0; j < tackEnd; j++) {
                    output.append(", ").append(nouns.remove(nouns.size() - 1));
                }
                output.append(".");
            }
            pw.println(output);
        }
        pw.close();
    }
}


