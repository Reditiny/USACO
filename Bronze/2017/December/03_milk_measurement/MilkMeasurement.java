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
        // 0 Bessie 1 Elsie 2 Mildred 按日期顺序依次记录三头牛的奶量变化量
        int[][] measurements = new int[101][3];
        for (int i = 0; i < n; i++) {
            String[] s = r.readLine().split(" ");
            switch (s[1]) {
                case "Bessie":
                    measurements[Integer.parseInt(s[0])][0] = Integer.parseInt(s[2]);
                    break;
                case "Elsie":
                    measurements[Integer.parseInt(s[0])][1] = Integer.parseInt(s[2]);
                    break;
                case "Mildred":
                    measurements[Integer.parseInt(s[0])][2] = Integer.parseInt(s[2]);
                    break;
            }
        }
        int ans = 0;
        // 0 Bessie 1 Elsie 2 Mildred 按日期顺序依次记录三头牛的奶量，每次记录后判断是否需要修改display
        int[] lastMilk = {7, 7, 7};
        boolean[] lastDisplay = {false, false, false};
        for (int i = 1; i <= 100; i++) {
            if (measurements[i][0] != 0 || measurements[i][1] != 0 || measurements[i][2] != 0) {
                int[] curMilk = lastMilk.clone();
                curMilk[0] += measurements[i][0];
                curMilk[1] += measurements[i][1];
                curMilk[2] += measurements[i][2];
                boolean[] curDisplay = makeDisplay(curMilk);
                if (lastDisplay[0] != curDisplay[0] || lastDisplay[1] != curDisplay[1] || lastDisplay[2] != curDisplay[2]) {
                    ans++;
                    lastDisplay = curDisplay;
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

    /**
     * 根据当前产量决定展示哪些牛 展示的组合共10种
     */
    public static boolean[] makeDisplay(int[] curMilk) {
        boolean[] curDisplay = new boolean[3];
        if (curMilk[0] == curMilk[1] && curMilk[1] == curMilk[2]) {
            curDisplay = new boolean[]{true, true, true};
        } else if (curMilk[0] == curMilk[1]) {
            if (curMilk[0] > curMilk[2]) {
                curDisplay = new boolean[]{true, true, false};
            } else {
                curDisplay = new boolean[]{false, false, true};
            }
        } else if (curMilk[0] == curMilk[2]) {
            if (curMilk[0] > curMilk[1]) {
                curDisplay = new boolean[]{true, false, true};
            } else {
                curDisplay = new boolean[]{false, true, false};
            }
        } else if (curMilk[1] == curMilk[2]) {
            if (curMilk[1] > curMilk[0]) {
                curDisplay = new boolean[]{false, true, true};
            } else {
                curDisplay = new boolean[]{true, false, false};
            }
        } else {
            if (curMilk[0] > curMilk[1] && curMilk[0] > curMilk[2]) {
                curDisplay = new boolean[]{true, false, false};
            } else if (curMilk[1] > curMilk[0] && curMilk[1] > curMilk[2]) {
                curDisplay = new boolean[]{false, true, false};
            } else {
                curDisplay = new boolean[]{false, false, true};
            }
        }
        return curDisplay;
    }
}


