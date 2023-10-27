import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class Triangles {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("triangles.in"));
        PrintWriter pw = new PrintWriter("triangles.out");
        int n = Integer.parseInt(r.readLine());
        List<List<Integer>> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            List<Integer> point = new ArrayList<>();
            point.add(Integer.parseInt(st.nextToken()));
            point.add(Integer.parseInt(st.nextToken()));
            points.add(point);
        }
        // 按照 x 坐标排序 如果 x 坐标相同则按照 y 坐标排序
        points.sort((o1, o2) -> {
            if (o1.get(0).equals(o2.get(0))) {
                return o1.get(1).compareTo(o2.get(1));
            }
            return o1.get(0).compareTo(o2.get(0));
        });
        int ans = 0;
        // 枚举所有可能的三角形 一边与x轴平行 一边与y轴平行
        for (int i = 0; i < n; i++) {
            List<Integer> point1 = points.get(i);
            for (int j = i + 1; j < n; j++) {
                List<Integer> point2 = points.get(j);
                for (int k = j + 1; k < n; k++) {
                    List<Integer> point3 = points.get(k);
                    ans = Math.max(ans, getArea(point1, point2, point3));
                }
            }
        }
        pw.println(ans);
        pw.close();
    }

    /**
     * 获得三角形面积
     * 表示三点不符合
     * 点集排序为按照 x 坐标排序 如果 x 坐标相同则按照 y 坐标排序
     * 符合条件的排列如下所示
     *   3    2 3    2      1 3
     * 1 2    1      1 3      2
     * y1 == y2 时为 组合1
     * x1 == x2 时为 组合2 组合3
     * y1 == y3 && x2 == x3 时 为组合4
     */
    public static int getArea(List<Integer> point1, List<Integer> point2, List<Integer> point3) {
        if (point1.get(1).equals(point2.get(1)) && point2.get(0).equals(point3.get(0))) {
            return (point2.get(0) - point1.get(0)) * (point3.get(1) - point1.get(1));
        }
        if (point1.get(0).equals(point2.get(0))) {
            if (point2.get(1).equals(point3.get(1)) || point1.get(1).equals(point3.get(1))) {
                return (point2.get(1) - point1.get(1)) * (point3.get(0) - point1.get(0));
            }
        }
        if (point1.get(1).equals(point3.get(1)) && point2.get(0).equals(point3.get(0))) {
            return (point3.get(0) - point1.get(0)) * (point3.get(1) - point2.get(1));
        }
        return 0;
    }
}
