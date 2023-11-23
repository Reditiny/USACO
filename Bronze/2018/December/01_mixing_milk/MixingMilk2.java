import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class MixingMilk2 {
    static int[] bucketCapacity = new int[3];
    static int[] bucketCurMilk = new int[3];

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("mixmilk.in"));
        PrintWriter pw = new PrintWriter("mixmilk.out");
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            bucketCapacity[i] = Integer.parseInt(st.nextToken());
            bucketCurMilk[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < 100; i++) {
            int from = i % 3;
            int to = (i + 1) % 3;
            int mount = Math.min(bucketCurMilk[from], bucketCapacity[to] - bucketCurMilk[to]);
            bucketCurMilk[from] -= mount;
            bucketCurMilk[to] += mount;
        }
        for (int i = 0; i < 3; i++) {
            pw.println(bucketCurMilk[i]);
        }
        pw.close();
    }
}