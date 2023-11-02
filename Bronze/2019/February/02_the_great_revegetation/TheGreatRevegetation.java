import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class TheGreatRevegetation {
    static int n;
    static int m;
    static List<List<Integer>> graph = new ArrayList<List<Integer>>();
    static List<Integer> grasses = new ArrayList<Integer>();

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("revegetate.in"));
        PrintWriter pw = new PrintWriter("revegetate.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        m = Integer.parseInt(st.nextToken());
        // 记录牧场的边关系(一头牛喜欢的两个牧场之间有条边) 邻接表 有边相连的牧场不能种相同的种子
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(r.readLine());
            int pasture1 = Integer.parseInt(st.nextToken()) - 1;
            int pasture2 = Integer.parseInt(st.nextToken()) - 1;
            graph.get(pasture1).add(pasture2);
            graph.get(pasture2).add(pasture1);
        }
        grasses.add(1);
        // 从前往后依次确定牧场的种子
        for (int i = 1; i < n; i++) {
            boolean[] existGrasses = new boolean[4];
            // 对于当前的 i 牧场，查看所有与 i 有边相连的 neighbor 牧场
            for (int neighbor : graph.get(i)) {
                // 如果 neighbor 已经种过种子了就把该种子标记为已经存在，表明 i 牧场不能种该种子
                if (neighbor < grasses.size()) {
                    existGrasses[grasses.get(neighbor) - 1] = true;
                }
            }
            // 找到 i 牧场可以种的第一种种子，因为题目有要求按多解时按小序列输出
            for (int j = 0; j < 4; j++) {
                if (!existGrasses[j]) {
                    grasses.add(j + 1);
                    break;
                }
            }
        }
        grasses.forEach(pw::print);
        pw.close();
    }
}
