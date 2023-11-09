import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Red
 * @version 1.0
 */
public class YearOfTheCow {
    static Map<String, Integer> zodiacMap = new HashMap<>();
    static Map<String, Integer> ageMap = new HashMap<>();
    static Map<String, String> cowZodiac = new HashMap<>();

    public static void main(String[] args) throws Exception {
        cowZodiac.put("Bessie", "Ox");
        ageMap.put("Bessie", 0);
        zodiacMap.put("Ox", 1);
        zodiacMap.put("Tiger", 2);
        zodiacMap.put("Rabbit", 3);
        zodiacMap.put("Dragon", 4);
        zodiacMap.put("Snake", 5);
        zodiacMap.put("Horse", 6);
        zodiacMap.put("Goat", 7);
        zodiacMap.put("Monkey", 8);
        zodiacMap.put("Rooster", 9);
        zodiacMap.put("Dog", 10);
        zodiacMap.put("Pig", 11);
        zodiacMap.put("Rat", 12);
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(r.readLine());
        for (int i = 0; i < n; i++) {
            String[] strings = r.readLine().split(" ");
            // 题目输入保证了 strings[0] 为第一次出现的牛 strings[7] 为出现过的牛
            // 通过已有的 strings[7] 的信息来推断 strings[0] 的信息
            String newCowName = strings[0];
            String newCowZodiac = strings[4];
            cowZodiac.put(newCowName, newCowZodiac);
            String oldCowName = strings[7];
            String oldCowZodiac = cowZodiac.get(oldCowName);
            int diff = 0;
            if ("previous".equals(strings[3])) {
                diff = zodiacMap.get(oldCowZodiac) - zodiacMap.get(newCowZodiac);
                if (diff <= 0) {
                    diff += 12;
                }
                ageMap.put(newCowName, ageMap.get(oldCowName) - diff);
            } else {
                diff = zodiacMap.get(newCowZodiac) - zodiacMap.get(oldCowZodiac);
                if (diff <= 0) {
                    diff += 12;
                }
                ageMap.put(newCowName, ageMap.get(oldCowName) + diff);
            }
        }
        pw.println(Math.abs(ageMap.get("Elsie")));
        pw.close();
    }
}
