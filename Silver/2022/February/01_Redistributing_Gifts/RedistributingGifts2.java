import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class RedistributingGifts2 {
    static int N;
    // 牛 -> 喜欢的礼物(包括自己本身的)
    static List<List<Integer>> preferences;
    // reachable[i][j] 表示牛i能否获得牛j的礼物
    static boolean[][] reachable;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        N = Integer.parseInt(r.readLine());
        preferences = new ArrayList<>();
        preferences.add(new ArrayList<>());
        for (int i = 1; i <= N; i++) {
            ArrayList<Integer> p = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(r.readLine());
            for (int j = 0; j < N; j++) {
                p.add(Integer.parseInt(st.nextToken()));
            }
            while (p.get(p.size() - 1) != i) {
                p.remove(p.size() - 1);
            }
            preferences.add(p);
        }
        reachable = new boolean[N + 1][N + 1];
        // 遍历所有可以直接得到喜欢的礼物的牛
        // 如果 cow1 喜欢 p 的礼物，那么 p 一定可以获得 cow1 的礼物，因为 cow1 愿意和 p 交换
        // 但是 cow1 不一定可以获得 p 的礼物，因为 p 不一定愿意和 cow1 交换
        for (int cow1 = 1; cow1 <= N; cow1++) {
            for (int p : preferences.get(cow1)) {
                reachable[p][cow1] = true;
            }
        }
        // 已知 reachable[p][cow1] 一定为 true 但 reachable[cow1][p] 未知
        // 当 reachable[cow1][p] 为 true 时，cow1 和 p 可以构成环，即 cow1 可以获得 p 的礼物
        // 例如 A,B,C 三头牛，其中A喜欢B的礼物，B喜欢C的礼物，C喜欢A的礼物
        // 有 reachable[B][A] = true, reachable[C][B] = true, reachable[A][C] = true
        // 通过传递性得到 reachable[C][A] = true, 说明 C 最后会得到 A 的礼物
        // 最后考虑为什么 cow2 中间牛要放在最外层循环，因为任意一头牛作为中间牛时，都需要考虑所有的牛的两两组合能否通过 cow2 作为中间牛而连通
        // 只有当 cow2 在最外层时，内层的循环可以在当前 cow2 下完成所有的两两组合的判断
        for (int cow2 = 1; cow2 <= N; cow2++) {
            for (int cow1 = 1; cow1 <= N; cow1++) {
                for (int cow3 = 1; cow3 <= N; cow3++) {
                    reachable[cow1][cow3] = reachable[cow1][cow3] || (reachable[cow1][cow2] && reachable[cow2][cow3]);
                }
            }
        }
        // 遍历所有牛，找到第一个可以获得的喜欢的礼物
        for (int cow = 1; cow <= N; cow++) {
            for (int p : preferences.get(cow)) {
                if (reachable[cow][p]) {
                    pw.println(p);
                    break;
                }
            }
        }
        pw.close();
    }
}