import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * @author Red
 * @version 1.0
 */
public class CircularBarn {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("cbarn.in"));
        PrintWriter pw = new PrintWriter("cbarn.out");
        int n = Integer.parseInt(r.readLine());
        // 记录每个房间牛的数量 以及牛的总数
        int[] rooms = new int[n];
        int cowCount = 0;
        for (int i = 0; i < n; i++) {
            rooms[i] = Integer.parseInt(r.readLine());
            cowCount += rooms[i];
        }
        // 从零号房间进入的总距离
        int lastDistance = 0;
        for (int i = 1; i < n; i++) {
            lastDistance += i * rooms[i];
        }
        // 依次计算从下一个房间进入的总距离
        int minDistance = lastDistance;
        for (int i = 1; i < n; i++) {
            // 当入口从i改为i+1时，相对于i为入口时的距离，i号房间的牛要多走一圈，其他牛都少走1步
            int curDistance = lastDistance - (cowCount - rooms[i - 1]) + (n - 1) * rooms[i - 1];
            if (curDistance < minDistance) {
                minDistance = curDistance;
            }
            lastDistance = curDistance;
        }
        pw.println(minDistance);
        pw.close();
    }
}

