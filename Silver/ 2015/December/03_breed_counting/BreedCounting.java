import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class BreedCounting {
    static int N, Q;
    // 一维 0 1 2 分别表示 Holsteins Guernseys Jerseys
    // 二维 i 表示前 i 个牛中对应品种的数量
    static int[][] breedPrefixSum;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("bcount.in"));
        PrintWriter pw = new PrintWriter("bcount.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        breedPrefixSum = new int[3][N + 1];
        for (int i = 1; i <= N; i++) {
            int breed = Integer.parseInt(r.readLine()) - 1;
            breedPrefixSum[0][i] = breedPrefixSum[0][i - 1];
            breedPrefixSum[1][i] = breedPrefixSum[1][i - 1];
            breedPrefixSum[2][i] = breedPrefixSum[2][i - 1];
            breedPrefixSum[breed][i]++;

        }
        // 第 a 头牛到第 b 头牛之间的数量
        // 前 b 头牛的数量 - 前 (a-1) 头牛的数量
        for(int i=0;i<Q;i++){
            st = new StringTokenizer(r.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pw.println(breedPrefixSum[0][b] - breedPrefixSum[0][a-1] + " " +
                    (breedPrefixSum[1][b] - breedPrefixSum[1][a-1]) + " " +
                    (breedPrefixSum[2][b] - breedPrefixSum[2][a-1]));
        }
        pw.close();
    }
}