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
        // 遍历所有可以间接得到喜欢的礼物的牛
        // 这里仅需要考虑一个中间牛即可
        // 如 cow1 喜欢 cow3 的礼物，cow3 喜欢 cow2 的礼物，cow2 喜欢 cow4 的礼物，cow4 喜欢 cow1 的礼物
        // 此时并不需要 cow4 和 cow2 才能把 cow1 和 cow3 连起来
        // 因为每次遍历都是从头开始遍历，假设 cow4 在 cow2 前被遍历到（反过来也一样），那么遍历到 cow4 时，cow1 和 cow2 的连通性已经被确定了
        // 由此可确定不管中间有多少牛，每次迭代都会减少一个中间牛，最终的表现就是只需要考虑一个中间牛即可
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