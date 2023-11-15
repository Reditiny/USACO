import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */

public class SnowBoots2 {
    static List<List<Integer>> boots = new ArrayList<>();
    static int[] snowDepth;
    static int[][] visited;
    static int n, b;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("snowboots.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("snowboots.out"));
        StringTokenizer st = new StringTokenizer(r.readLine());
        n = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        snowDepth = new int[n];
        visited = new int[n][b];
        st = new StringTokenizer(r.readLine());
        for (int i = 0; i < n; i++) {
            snowDepth[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < b; i++) {
            st = new StringTokenizer(r.readLine());
            List<Integer> boot = new ArrayList<>();
            boot.add(Integer.parseInt(st.nextToken()));
            boot.add(Integer.parseInt(st.nextToken()));
            boots.add(boot);
        }
        visited[0][0] = 1;
        // 遍历每个鞋和每个砖块
        for (int boot = 0; boot < b; boot++) {
            int topDepth = boots.get(boot).get(0);
            int topSize = boots.get(boot).get(1);
            for (int location = 0; location < n; location++) {
                // 当前的鞋子无法站住当前的砖块上
                if (snowDepth[location] > topDepth) {
                    visited[location][boot] = -1;
                    continue;
                }
                // 穿着当前的鞋子，前一块可能的砖块
                for (int priorLocation = 0; priorLocation < location; priorLocation++) {
                    if (visited[priorLocation][boot] == 1 && priorLocation + topSize >= location) {
                        visited[location][boot] = 1;
                    }
                }
                // 当前的砖块，前一双可能的鞋子
                for (int priorBoot = 0; priorBoot < boot; priorBoot++) {
                    if (visited[location][priorBoot] == 1) {
                        visited[location][boot] = 1;
                    }
                }
            }
            if (visited[n - 1][boot] == 1) {
                pw.println(boot);
                break;
            }
        }
        pw.close();
    }
}