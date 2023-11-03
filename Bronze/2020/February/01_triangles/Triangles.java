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
        // 按照 x 坐标排序
        points.sort((o1, o2) -> {
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
     * 获得三角形面积(x1,y1)(x2,y2)(x3,y3) 输出 0 表示三点不符合条件
     * x1 == x2 时需要有 y3 == y2 || y3 == y1
     * x3 == x2 时需要有 y1 == y2 || y1 == y3
     */
    public static int getArea(List<Integer> point1, List<Integer> point2, List<Integer> point3) {
        int dx = 0;
        int dy = 0;
        if (point1.get(0).equals(point2.get(0))) {
            dy = Math.abs(point2.get(1) - point1.get(1));
            if (point2.get(1).equals(point3.get(1)) || point1.get(1).equals(point3.get(1))) {
                dx = Math.abs(point1.get(0) - point3.get(0));
            }
        } else if (point3.get(0).equals(point2.get(0))) {
            dy = Math.abs(point3.get(1) - point2.get(1));
            if (point3.get(1).equals(point1.get(1)) || point2.get(1).equals(point1.get(1))) {
                dx = Math.abs(point2.get(0) - point1.get(0));
            }
        }
        return dx * dy;
    }
}
