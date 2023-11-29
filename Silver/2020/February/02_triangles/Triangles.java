import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class Triangles {
    static int N;
    static int M = (int) 1e9 + 7;
    static int D = (int) 1e4;
    static Fence[] fences;
    // xToYs 存储 x 坐标对应的 y 坐标和 fence 序号
    static Map<Integer, List<List<Integer>>> xToYs = new HashMap<>();
    // yToXs 存储 y 坐标对应的 x 坐标和 fence 序号
    static Map<Integer, List<List<Integer>>> yToXs = new HashMap<>();
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("triangles.in"));
        PrintWriter pw = new PrintWriter("triangles.out");
        N = Integer.parseInt(r.readLine());
        fences = new Fence[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            fences[i] = new Fence(x, y);
            xToYs.putIfAbsent(x, new ArrayList<>());
            xToYs.get(x).add(Arrays.asList(y, i));
            yToXs.putIfAbsent(y, new ArrayList<>());
            yToXs.get(y).add(Arrays.asList(x, i));
        }
        // 根据横坐标来计算以 (x,yList[j]) 为顶点的栅栏的高度和
        xToYs.forEach((x, yList) -> {
            if (yList != null) {
                long cur = 0;
                yList.sort(Comparator.comparingInt(o -> o.get(0)));
                for (int j = 1; j < yList.size(); j++) {
                    cur += yList.get(j).get(0) - yList.get(0).get(0) ;
                }
                fences[yList.get(0).get(1)].heightSum = cur;
                for (int j = 1; j < yList.size(); j++) {
                    cur += (2L * j - yList.size()) * (yList.get(j).get(0) - yList.get(j - 1).get(0));
                    fences[yList.get(j).get(1)].heightSum = cur;
                }
            }
        });
        // 根据纵坐标来计算以 (xList[j],y) 为顶点的栅栏的底边和
        yToXs.forEach((y, xList) -> {
            if (xList != null) {
                long cur = 0;
                xList.sort(Comparator.comparingInt(o -> o.get(0)));
                for (int j = 1; j < xList.size(); j++) {
                    cur += xList.get(j).get(0) - xList.get(0).get(0);
                }
                fences[xList.get(0).get(1)].baseSum = cur;
                for (int j = 1; j < xList.size(); j++) {
                    cur += (2L * j - xList.size()) * (xList.get(j).get(0) - xList.get(j - 1).get(0));
                    fences[xList.get(j).get(1)].baseSum = cur;
                }
            }
        });
        ans = 0;
        for (int i = 0; i < N; i++) {
            ans += fences[i].heightSum * fences[i].baseSum % M;
            ans %= M;
        }
        pw.println(ans);
        pw.close();
    }

    /**
     * (x,y)为直角顶点
     * heightSum 为所有栅以(x,y)为直角顶点的栅栏的高度和
     * baseSum 为所有栅以(x,y)为直角顶点的栅栏的底边长度和
     * 因为这些栅栏共用一个直角顶点所以面积和为 heightSum * baseSum
     * 例如如下五个点所构成的栅栏
     * . y2
     * . y1
     * .   .    .
     * 0   x1   x2
     * areaSum = x1 * y1 + x1 * y2 + x2 * y1 + x2 + y2
     * = (x1 + x2) * (y1 + y2)
     * 其中 x1 和 x2 是栅栏的底边的两个情况，y1 和 y2 是栅栏的高度的两个情况
     * 由此得到以(x,y)为直角顶点的栅栏的面积和为 heightSum * baseSum
     */
    static class Fence {
        int x;
        int y;
        public long heightSum;
        public long baseSum;

        public Fence(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}