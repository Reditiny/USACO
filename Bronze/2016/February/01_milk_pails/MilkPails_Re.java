import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class MilkPails_Re {
    static int X, Y, M;
    static int result = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("pails.in"));
        PrintWriter pw = new PrintWriter("pails.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dfs(0);
        pw.println(result);
        pw.close();
    }

    static void dfs(int acc) {
        if (acc > M) {
            return;
        }
        result = Math.max(result, acc);
        dfs(acc + X);
        dfs(acc + Y);
    }
}
