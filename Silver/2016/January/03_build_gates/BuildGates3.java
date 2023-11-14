import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class BuildGates3 {
    static int n;
    static HashSet<List<List<Integer>>> visited_edges = new HashSet<>();
    static HashSet<List<Integer>> visited_vertices = new HashSet<List<Integer>>();
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
        int ans = 0;
        int lastX = 0, lastY = 0;
        int curX = 0, curY = 0;
        visited_vertices.add(Arrays.asList(curX, curY));
        for (char step : r.readLine().toCharArray()) {
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
            List<List<Integer>> edge = Arrays.asList(node1, node2);
            List<List<Integer>> reverseEdge = Arrays.asList(node2, node1);
            // 创建边的过程中 一个新的边连到已经存在的点上就会隔出一块封闭图形 需要多一个门让这块图形和外面连通
            if(!visited_edges.contains(edge) && !visited_edges.contains(reverseEdge)
                    && visited_vertices.contains(node2)) {
                ans++;
            }
            visited_edges.add(edge);
            visited_vertices.add(node2);
        }
        pw.println(ans);
        pw.close();
    }
}