import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author Red
 * @version 1.0
 */
public class TheBovineShuffle {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("shuffle.in"));
        PrintWriter pw = new PrintWriter("shuffle.out");
        int n = Integer.parseInt(r.readLine());
        // shuffle 方式   当前顺序
        String[] shuffle = r.readLine().split(" ");
        String[] order = r.readLine().split(" ");
        // 根据 shuffle 方式重新排列三次
        for (int i = 0; i < 3; i++) {
            String[] temp = new String[n];
            for (int j = 0; j < n; j++) {
                temp[j] = order[Integer.parseInt(shuffle[j]) - 1];
            }
            order = temp;
        }
        for (int i = 0; i < n; i++) {
            pw.println(order[i]);
        }
        pw.close();
    }
}


