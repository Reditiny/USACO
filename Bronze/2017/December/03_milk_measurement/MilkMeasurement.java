import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * @author Red
 * @version 1.0
 */
public class MilkMeasurement {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("measurement.in"));
        PrintWriter pw = new PrintWriter("measurement.out");
        int n = Integer.parseInt(r.readLine());
        Measurement[] measurements = new Measurement[101];
        for (int i = 0; i < n; i++) {
            String[] s = r.readLine().split(" ");
            measurements[Integer.parseInt(s[0])] = new Measurement(s[1], Integer.parseInt(s[2]));
        }
        int ans = 0;
        // 0 Bessie 1 Elsie 2 Mildred 按日期顺序依次记录三头牛的奶量，每次记录后判断是否需要修改display
        int[] lastMilk = {7, 7, 7};
        for (int i = 1; i <= 100; i++) {
            if (measurements[i] != null) {
                int[] curMilk = lastMilk.clone();
                switch (measurements[i].name) {
                    case "Bessie":
                        curMilk[0] += measurements[i].change;
                        break;
                    case "Elsie":
                        curMilk[1] += measurements[i].change;
                        break;
                    case "Mildred":
                        curMilk[2] += measurements[i].change;
                        break;
                }
                if (!sameMax(lastMilk, curMilk)) {
                    ans++;
                }
                lastMilk = curMilk;
            }
        }
        pw.println(ans);
        pw.close();
    }

    /**
     * 判断两个数组中最大值的位置是否相同
     * 即是否修改display的逻辑
     */
    public static boolean sameMax(int[] lastMilk, int[] curMilk) {
        return (lastMilk[0] == lastMilk[1] && lastMilk[2] == lastMilk[0]) && (curMilk[0] == curMilk[1] && curMilk[2] == curMilk[0]) ||

                (lastMilk[0] > lastMilk[1] && lastMilk[0] > lastMilk[2]) && (curMilk[0] > curMilk[1] && curMilk[0] > curMilk[2]) ||
                (lastMilk[1] > lastMilk[0] && lastMilk[1] > lastMilk[2]) && (curMilk[1] > curMilk[0] && curMilk[1] > curMilk[2]) ||
                (lastMilk[2] > lastMilk[0] && lastMilk[2] > lastMilk[1]) && (curMilk[2] > curMilk[0] && curMilk[2] > curMilk[1]) ||

                (lastMilk[0] == lastMilk[1] && lastMilk[1] > lastMilk[2]) && (curMilk[0] == curMilk[1] && curMilk[1] > curMilk[2]) ||
                (lastMilk[0] == lastMilk[2] && lastMilk[2] > lastMilk[1]) && (curMilk[0] == curMilk[2] && curMilk[2] > curMilk[1]) ||
                (lastMilk[1] == lastMilk[2] && lastMilk[2] > lastMilk[0]) && (curMilk[1] == curMilk[2] && curMilk[2] > curMilk[0]);
    }
}

class Measurement {
    String name;
    int change;

    public Measurement(String name, int change) {
        this.name = name;
        this.change = change;
    }
}
