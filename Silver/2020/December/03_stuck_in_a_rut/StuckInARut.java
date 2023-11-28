import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class StuckInARut {
    static int N;
    static int[] xCoordinates;
    static int[] yCoordinates;
    static List<Integer> eastCows = new ArrayList<>();
    static List<Integer> northCows = new ArrayList<>();
    static boolean[] stopped;
    static int[] stopCount;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        N = Integer.parseInt(r.readLine());
        xCoordinates = new int[N];
        yCoordinates = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            String direction = st.nextToken();
            if (direction.equals("E")) {
                eastCows.add(i);
            } else {
                northCows.add(i);
            }
            xCoordinates[i] = Integer.parseInt(st.nextToken());
            yCoordinates[i] = Integer.parseInt(st.nextToken());
        }
        // 向北的牛按横坐标从小到大排序，向东的牛按纵坐标从小到大排序
        eastCows.sort(Comparator.comparingInt(j -> yCoordinates[j]));
        northCows.sort(Comparator.comparingInt(j -> xCoordinates[j]));

        stopped = new boolean[N];
        stopCount = new int[N];
        for (int j : eastCows) {
            for (int k : northCows) {
                // 两牛都没停
                // 向东的牛只有可能被横坐标更大的向北的牛阻挡，向北的牛只有可能被纵坐标更大的向东的牛阻挡
                if ((!stopped[j]) && (!stopped[k]) &&
                        (xCoordinates[k] > xCoordinates[j]) &&
                        (yCoordinates[j] > yCoordinates[k])) {
                    if ((xCoordinates[k] - xCoordinates[j]) > (yCoordinates[j] - yCoordinates[k])) {
                        // 向北的牛挡住了向东的牛
                        stopped[j] = true;
                        // 阻挡的传递性
                        stopCount[k] += (1 + stopCount[j]);
                    } else if ((xCoordinates[k] - xCoordinates[j]) < (yCoordinates[j] - yCoordinates[k])) {
                        // 向东的牛挡住了向北的牛
                        stopped[k] = true;
                        stopCount[j] += (1 + stopCount[k]);
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) { pw.println(stopCount[i]); }
        pw.close();
    }
}