import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class FieldReductionBronze {
    static int minX = Integer.MAX_VALUE, secondMinX = Integer.MAX_VALUE, secondMaxX = 0, maxX = 0;
    static int minY= Integer.MAX_VALUE, secondMinY= Integer.MAX_VALUE, secondMaxY=0, maxY=0;
    static int n;
    static int ans;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("reduce.in"));
        PrintWriter pw = new PrintWriter("reduce.out");
        n = Integer.parseInt(r.readLine());
        int[] x = new int[n];
        int[] y = new int[n];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
            updateMinMax(x[i], y[i]);
        }
        ans = (maxX - minX) * (maxY - minY);
        // 遍历每个点，得到去除该点后的最小面积
        // 因为维护了最大次大最小次小值，所以求面积的复杂度为O(1)
        for(int i = 0; i < n; i++) {
            int curMinX = minX;
            if(x[i] == curMinX) {
                curMinX = secondMinX;
            }
            int curMaxX = maxX;
            if(x[i] == curMaxX) {
                curMaxX = secondMaxX;
            }
            int curMinY = minY;
            if(y[i] == curMinY) {
                curMinY = secondMinY;
            }
            int curMaxY = maxY;
            if(y[i] == curMaxY) {
                curMaxY = secondMaxY;
            }
            ans = Math.min(ans, (curMaxX - curMinX) * (curMaxY - curMinY));
        }
        pw.println(ans);
        pw.close();
    }

    /**
     * 更新最大次大最小次小值
     */
    public static void updateMinMax(int x, int y) {
        if(x < minX) {
            secondMinX = minX;
            minX = x;
        }
        else if(x < secondMinX) {
            secondMinX = x;
        }
        if(x > maxX) {
            secondMaxX = maxX;
            maxX = x;
        }
        else if(x > secondMaxX) {
            secondMaxX = x;
        }

        if(y < minY) {
            secondMinY = minY;
            minY = y;
        }
        else if(y < secondMinY) {
            secondMinY = y;
        }
        if(y > maxY) {
            secondMaxY = maxY;
            maxY = y;
        }
        else if(y > secondMaxY) {
            secondMaxY = y;
        }
    }
}
