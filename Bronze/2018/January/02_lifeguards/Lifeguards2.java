import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Red
 * @version 1.0
 */
public class Lifeguards2 {
    static List<List<Integer>> liveGuards;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("lifeguards.in"));
        PrintWriter pw = new PrintWriter("lifeguards.out");
        List<List<Integer>> intervals = new ArrayList<List<Integer>>();
        int n = Integer.parseInt(r.readLine());
        liveGuards = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] s = r.readLine().split(" ");
            List<Integer> liveGuard = new ArrayList<>();
            liveGuard.add(Integer.parseInt(s[0]));
            liveGuard.add(Integer.parseInt(s[1]));
            liveGuards.add(liveGuard);
        }
        int ans = 0;
        // 依次计算开除救生员 i 之后可以覆盖的时间
        for(int i = 0; i < liveGuards.size(); i++){
            ans = Math.max(ans, coverTimeMissOne(i));
        }
        pw.println(ans);
        pw.close();
    }

    /**
     *  计算开除指定救生员后可以覆盖的时间
     */
    static int coverTimeMissOne(int missed){
        int[] lifeGuardCover = new int[1001];
        for(int i = 0;i<liveGuards.size();i++){
            if(i == missed){
                continue;
            }
            for(int j = liveGuards.get(i).get(0);j<liveGuards.get(i).get(1);j++){
                lifeGuardCover[j]++;
            }
        }
        int coverCount = 0;
        for(int i = 0;i<lifeGuardCover.length;i++){
            if(lifeGuardCover[i] > 0){
                coverCount++;
            }
        }
        return coverCount;
    }
}
