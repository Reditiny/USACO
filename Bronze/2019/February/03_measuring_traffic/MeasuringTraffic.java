import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class MeasuringTraffic {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("traffic.in"));
        PrintWriter pw = new PrintWriter("traffic.out");
        int n = Integer.parseInt(r.readLine());
        String[] typeList = new String[n];
        int[] minList = new int[n];
        int[] maxList = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            typeList[i] = st.nextToken();
            minList[i] = Integer.parseInt(st.nextToken());
            maxList[i] = Integer.parseInt(st.nextToken());
        }
        // 从前往后依次合并每个Sensor的数据
        int min = 0;
        int max = 1000;
        for (int i = n - 1; i >= 0; i--) {
            switch (typeList[i]) {
                case "none":
                    min = Math.max(min, minList[i]);
                    max = Math.min(max, maxList[i]);
                    break;
                case "off":
                    min += minList[i];
                    max += maxList[i];
                    break;
                case "on":
                    max -= minList[i];
                    min -= maxList[i];
                    break;
                default:
                    break;
            }
            min = Math.max(0, min);
        }
        pw.println(min + " " + max);
        // 从后往前依次合并每个Sensor的数据   注意从后往前时 off 和 on 的逻辑应该相反
        min = 0;
        max = 1000;
        for (int i = 0; i < n; i++) {
            switch (typeList[i]) {
                case "none":
                    min = Math.max(min, minList[i]);
                    max = Math.min(max, maxList[i]);
                    break;
                case "on":
                    min += minList[i];
                    max += maxList[i];
                    break;
                case "off":
                    max -= minList[i];
                    min -= maxList[i];
                    break;
                default:
                    break;
            }
            min = Math.max(0, min);
        }
        pw.println(min + " " + max);
        pw.close();
    }
}
