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
public class DontBeLast2 {
    static Map<String, Integer> cowToMilk = new HashMap<String, Integer>();
    static String ansCow = "Tie";

    public static void main(String[] args) throws Exception {
        // map 存储每头奶牛的奶量，初始值 0 必须要给，因为有些奶牛可能不出现在产奶日志上
        cowToMilk.put("Bessie", 0);
        cowToMilk.put("Elsie", 0);
        cowToMilk.put("Daisy", 0);
        cowToMilk.put("Gertie", 0);
        cowToMilk.put("Annabelle", 0);
        cowToMilk.put("Maggie", 0);
        cowToMilk.put("Henrietta", 0);
        BufferedReader r = new BufferedReader(new FileReader("notlast.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("notlast.out"));
        int n = Integer.parseInt(r.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            String cow = st.nextToken();
            int milk = Integer.parseInt(st.nextToken());
            cowToMilk.put(cow, cowToMilk.get(cow) + milk);
        }
        // 根据奶量去重排序，如果第二小的奶量只有一个，那么就是答案，否则就是 Tie
        int[] sortedValues = cowToMilk.values().stream().distinct().sorted().mapToInt(Integer::intValue).toArray();
        if (sortedValues.length > 1) {
            int secondMin = sortedValues[1];
            for (Map.Entry<String, Integer> entry : cowToMilk.entrySet()) {
                if (entry.getValue() == secondMin) {
                    // 如果 ansCow ！= "Tie"，说明有两头奶牛的奶量都是第二小的
                    if ("Tie".equals(ansCow)) {
                        ansCow = entry.getKey();
                    } else {
                        ansCow = "Tie";
                        break;
                    }
                }
            }
        }
        pw.println(ansCow);
        pw.close();
    }
}
