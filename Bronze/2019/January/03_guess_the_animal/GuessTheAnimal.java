import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class GuessTheAnimal {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("guess.in"));
        PrintWriter pw = new PrintWriter("guess.out");
        int n = Integer.parseInt(r.readLine());
        // 动物编号 -> 特征集合
        HashSet<String>[] animals = new HashSet[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(r.readLine());
            line.nextToken();
            int k = Integer.parseInt(line.nextToken());
            animals[i] = new HashSet<>();
            for (int j = 0; j < k; j++) {
                animals[i].add(line.nextToken());
            }
        }
        int ans = 0;
        // 枚举所有可能的两两组合求交集大小 因为其他动物全都会在这个交集中的问题时被剔除
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                HashSet<String> animalI = new HashSet<>(animals[i]);
                animalI.retainAll(animals[j]);
                ans = Math.max(ans, animalI.size() + 1);
            }
        }
        pw.println(ans);
        pw.close();
    }
}
