import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class BuildGates2 {
    static int n;
    // 边集
    static HashSet<List<List<Integer>>> edges = new HashSet<>();
    // 点集
    static HashSet<List<Integer>> vertices = new HashSet<List<Integer>>();
    static int[][] move = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("gates.in"));
        PrintWriter pw = new PrintWriter("gates.out");
        map.put('N', 0);
        map.put('S', 1);
        map.put('W', 2);
        map.put('E', 3);
        n = Integer.parseInt(r.readLine());
        int lastX = 0, lastY = 0;
        int curX = 0, curY = 0;
        vertices.add(Arrays.asList(curX, curY));
        char[] steps = r.readLine().toCharArray();
        for (char step : steps) {
            lastX = curX;
            lastY = curY;
            curX += move[map.get(step)][0];
            curY += move[map.get(step)][1];
            List<Integer> node1 = new ArrayList<>();
            node1.add(lastX);
            node1.add(lastY);
            List<Integer> node2 = new ArrayList<>();
            node2.add(curX);
            node2.add(curY);
            vertices.add(node2);
            if (step == 'N' || step == 'E') {
                edges.add(Arrays.asList(node1, node2));
            } else {
                edges.add(Arrays.asList(node2, node1));
            }
        }
        // 欧拉公式 顶点数 - 边数 + 面数 = 2
        // V - E + F = 2 => F = 2 - V + E
        // 面即一个封闭区域，门数 = 面数 - 1
        pw.println(2 - vertices.size() + edges.size() - 1);
        pw.close();
    }
}