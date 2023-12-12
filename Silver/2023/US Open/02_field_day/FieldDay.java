import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class FieldDay {
    static int C, N;
    static int[] teams;
    static int[] minEdits;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());

        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        teams = new int[N];
        minEdits = new int[1 << C];
        Arrays.fill(minEdits, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            String breeds = r.readLine();
            // 将 team 的字符串表示转换为二进制表示
            for (int j = 0; j < C; j++) {
                if (breeds.charAt(j) == 'G') {
                    teams[i] += 1 << (C - j - 1);
                }
            }
            // minEdits 存储了所有可能的二进制掩码的最小编辑次数
            // 其中只有 teams 中的值是所要求的，其他的都是冗余的
            minEdits[teams[i]] = 0;
        }

        for (int edit = 0; edit < C; edit++) {
            for (int mask = 0; mask < (1 << C); mask++) {
                // 所有可能的二进制掩码，就是 0 到 2^C - 1 的二进制表示
                if (minEdits[mask] != Integer.MAX_VALUE) {
                    // mask ^ (1 << edit) 将 mask 的第 edit 位取反
                    // 如 mask 代表 GHG，那么 mask ^ (1 << edit) 依次遍历 GHH、GGG、HHG
                    // 计算与 mask 只有一位不同的二进制掩码的最小编辑次数
                    minEdits[mask ^ (1 << edit)] = Math.min(minEdits[mask ^ (1 << edit)], minEdits[mask] + 1);
                }
            }
        }
        for (int i = 0; i < N; i++) {
            // 目标是求对于 teams[i] 的最大距离
            // 等价于求 teams[i] 的每个品种取反后的最小距离
            // teams[i] ^ ((1 << c) - 1) 实现所有位取反
            System.out.println(C - minEdits[teams[i] ^ ((1 << C) - 1)]);
        }
    }

}