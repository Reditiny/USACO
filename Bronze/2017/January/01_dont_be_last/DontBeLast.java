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
    static Map<String, Integer> cowToMilk = new HashMap<String, Integer>();
    static int minMilk = Integer.MAX_VALUE;
    static int secondMinMilk = Integer.MAX_VALUE;
    static int secondCount = 0;
    static String ansCow;

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
        // 确定最小值与次小值
        cowToMilk.forEach((cow, milk) -> {
            if(milk < minMilk){
                secondMinMilk = minMilk;
                minMilk = milk;
            }else if(milk > minMilk && milk < secondMinMilk){
                secondMinMilk = milk;
            }
        });
        // 计算次小值的数量以及次小值的牛
        cowToMilk.forEach((cow, milk) -> {
            if (milk == secondMinMilk) {
                secondCount++;
                ansCow = cow;
            }
        });
        if (secondCount != 1) {
            pw.println("Tie");
        } else {
            pw.println(ansCow);
        }
        pw.close();
    }
}
