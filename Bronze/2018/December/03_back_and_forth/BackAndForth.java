import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class BackAndForth {
    static List<Integer> buckets1 = new ArrayList<Integer>();
    static List<Integer> buckets2 = new ArrayList<Integer>();
    static HashSet<Integer> milks = new HashSet<Integer>();

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("backforth.in"));
        PrintWriter pw = new PrintWriter("backforth.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        for (int i = 0; i < 10; i++) {
            buckets1.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(r.readLine());
        for (int i = 0; i < 10; i++) {
            buckets2.add(Integer.parseInt(st.nextToken()));
        }
        move(new ArrayList<>(), buckets1, buckets2);
        pw.println(milks.size());
        pw.close();
    }

    /**
     * 递归移动
     * Java 语言中引用数据类型（非基本数据类型）均为引用传递
     * 在函数内用 copy 的数据传递给下一层可以达到本层的回溯效果
     */
    public static void move(List<Integer> milkMount, List<Integer> bucketsFrom, List<Integer> bucketsTo) {
        if (milkMount.size() == 4) {
            int mount = 1000;
            mount -= (milkMount.get(0) + milkMount.get(2));
            mount += (milkMount.get(1) + milkMount.get(3));
            milks.add(mount);
            return;
        }
        for (int i = 0; i < bucketsFrom.size(); i++) {
            List<Integer> bucketsFromCopy = new ArrayList<>(bucketsFrom);
            List<Integer> bucketsToCopy = new ArrayList<>(bucketsTo);
            List<Integer> milkMountCopy = new ArrayList<>(milkMount);
            int d = bucketsFromCopy.remove(i);
            bucketsToCopy.add(d);
            milkMountCopy.add(d);
            move(milkMountCopy, bucketsToCopy, bucketsFromCopy);
        }
    }
}
