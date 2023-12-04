import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class HighCardWins {
    static int N;
    // 0 表示为牛Bessie的牌 1 表示为牛Elsie的牌
    static int[] cards;
    static int ans = 0;
    static int elsieCardCount = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("highcard.in"));
        PrintWriter pw = new PrintWriter("highcard.out");
        N = Integer.parseInt(r.readLine());
        cards = new int[2*N+1];
        for (int i = 0; i < N; i++){
            cards[Integer.parseInt(r.readLine())] = 1;
        }
        // 从小到大遍历每张牌
        for (int i = 1; i <= 2*N; i++){
            // 如果是Elsie的牌
            if (cards[i] == 1){
                elsieCardCount++;
            } else {
                // 如果是Bessie的牌且前面还有Elsie没有出的牌
                // Bessie就可以用这张牌赢Elsie前面的一张牌
                if (elsieCardCount > 0){
                    elsieCardCount--;
                    ans++;
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}