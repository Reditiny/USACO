import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class DontBeLast {
    static Map<String, Integer> positionMap = new HashMap<String, Integer>();
    static int ansIndex;

    static {
        positionMap.put("Bessie", 0);
        positionMap.put("Elsie", 1);
        positionMap.put("Daisy", 2);
        positionMap.put("Gertie", 3);
        positionMap.put("Annabelle", 4);
        positionMap.put("Maggie", 5);
        positionMap.put("Henrietta", 6);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("notlast.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("notlast.out"));
        int n = Integer.parseInt(r.readLine());
        int[] milks = new int[positionMap.size()];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            String cow = st.nextToken();
            int milk = Integer.parseInt(st.nextToken());
            milks[positionMap.get(cow)] += milk;
        }
        // 依次确定最小值与次小值并确定次小值索引以及次小值数量
        int minMilk = Integer.MAX_VALUE;
        for (int i = 0; i < milks.length; i++) {
            minMilk = Math.min(minMilk, milks[i]);
        }
        int secondMinMilk = Integer.MAX_VALUE;
        for (int i = 0; i < milks.length; i++) {
            if (milks[i] > minMilk && milks[i] < secondMinMilk) {
                secondMinMilk = milks[i];
                ansIndex = i;
            }
        }
        int secondCount = 0;
        for (int i = 0; i < milks.length; i++) {
            if (milks[i] == secondMinMilk) {
                secondCount++;
            }
        }
        if (secondCount != 1) {
            pw.println("Tie");
        } else {
            positionMap.forEach((cow, index) -> {
                if (index == ansIndex) {
                    pw.println(cow);
                }
            });
        }
        pw.close();
    }
}
