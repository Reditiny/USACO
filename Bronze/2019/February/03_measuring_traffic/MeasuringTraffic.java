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
        List<Sensor> sensorList = new ArrayList<Sensor>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            String type = st.nextToken();
            int min = Integer.parseInt(st.nextToken());
            int max = Integer.parseInt(st.nextToken());
            sensorList.add(new Sensor(type, min, max));
        }
        // 从前往后依次合并每个Sensor的数据
        Sensor curSensor = new Sensor("none", 0, 1000);
        for (int i = n - 1; i >= 0; i--) {
            Sensor nextSensor = sensorList.get(i);
            switch (nextSensor.type) {
                case "none":
                    curSensor.min = Math.max(curSensor.min, nextSensor.min);
                    curSensor.max = Math.min(curSensor.max, nextSensor.max);
                    break;
                case "off":
                    curSensor.min += nextSensor.min;
                    curSensor.max += nextSensor.max;
                    break;
                case "on":
                    curSensor.max -= nextSensor.min;
                    curSensor.min -= nextSensor.max;
                    break;
                default:
                    break;
            }
            curSensor.min = Math.max(0, curSensor.min);
        }
        pw.print(curSensor.min);
        pw.print(" ");
        pw.println(curSensor.max);
        // 从后往前依次合并每个Sensor的数据   注意从后往前时 off 和 on 的逻辑应该相反
        curSensor = new Sensor("none", 0, 1000);
        for (int i = 0; i < n; i++) {
            Sensor nextSensor = sensorList.get(i);
            switch (nextSensor.type) {
                case "none":
                    curSensor.min = Math.max(curSensor.min, nextSensor.min);
                    curSensor.max = Math.min(curSensor.max, nextSensor.max);
                    break;
                case "on":
                    curSensor.min += nextSensor.min;
                    curSensor.max += nextSensor.max;
                    break;
                case "off":
                    curSensor.max -= nextSensor.min;
                    curSensor.min -= nextSensor.max;
                    break;
                default:
                    break;
            }
            curSensor.min = Math.max(0, curSensor.min);
        }
        pw.print(curSensor.min);
        pw.print(" ");
        pw.println(curSensor.max);
        pw.close();
    }
}

class Sensor {
    String type;
    int min;
    int max;

    Sensor(String type, int min, int max) {
        this.type = type;
        this.min = min;
        this.max = max;
    }
}
