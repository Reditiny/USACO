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
            // 仅需确定最终答案中使用了多少个 intransitive-verb transitive-verb 和 conjunction
            // 三者的数量可以确定 noun 和 period 的数量，最终多出的 noun 和 comma 可以全部接在最后一个 type2 句子后
            for (int type1 = 0; type1 <= iverb.size(); type1++) {
                // 1.遍历得到  intransitive-verb 的数量记为 type1
                int nounCount = nouns.size(), conjCount = conj.size();
                int period = p, comma = c;
                int curWords = 0;
                curWords += 2 * type1;
                nounCount -= type1;
                if (nounCount < 0) {    // 当前 type1 数 noun 数量不足
                    continue;
                }
                // 2.根据 noun 和 period 确定 transitive-verb 的数量记为 type2
                // type2 不能超过当前 noun 数量的一半
                int sentenceLimit = Math.min(tverb.size(), nounCount / 2);
                // 当存在连词时，一个句号可以对应两个 intransitive-verb；当不存在连词时，一个句号对于一个 intransitive-verb
                // 首先考虑有连词时，因为此时一个句号可以对应两个动词，之后再看句号有没有剩余
                int symbolLimit = Math.min(conjCount, period) * 2 + Math.max(0, period - conjCount);
                int type2 = Math.min(sentenceLimit, symbolLimit);
                curWords += 3 * type2;
                nounCount -= 2 * type2;
                // 3.确定可以使用的 conjunction 数量记为 canCombine
                int canCombine = Math.min((type1 + type2) / 2, conjCount);
                curWords += canCombine;
                // 判断 period 的数量是否满足当前 type1 type2 canCombine 的组合
                if (period < (type1 + type2) - canCombine) {
                    continue;
                }
                // 多余的动词可以用 , noun 接在最后一个 type2 句子后面
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
                // 从数组中移除任意一个数据应该优先从末尾移除
                // 因为数组的存储方式为顺序存储，移除末尾元素直接移除即可，复杂度为 O(1)
                // 若要移除首元素则需要将后续所有元素都向前移动一位，复杂度为 O(n)
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
                    // 此处不建议使用以下两种方式 因为他们都会构造新的 "k " 字符串带来额外开销
                    // output.append(String.join(k, " "));
                    // output.append(k + " ");
                    output.append(k).append(" ");
                }
                if (j % 2 == 0 && j != sentences.size() - 1 && combine > 0) {
                    // 每两个句子可以插入一个连词 且当前句子不能为最后一个句子
                    combine--;
                    output.append(conj.remove(conj.size() - 1)).append(" ");
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


