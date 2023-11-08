import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class CitiesAndStates {
    static int n;
    static Map<String, Integer> codeAndStateToCount = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("citystate.in"));
        PrintWriter pw = new PrintWriter("citystate.out");
        n = Integer.parseInt(r.readLine());
        int ans = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            String cityFirstTowCode = st.nextToken().substring(0, 2);
            String code = st.nextToken();
            String cityAndState = cityFirstTowCode + code;
            String stateAndCity = code + cityFirstTowCode;
            if (cityAndState.equals(stateAndCity)) {
                continue;
            }
            // map 中存储 stateAndCity 的数量
            // 在遍历 cityAndState 时，如果 map 中 stateAndCity，那么该数量就符合要求的城市对
            if (codeAndStateToCount.containsKey(cityAndState)) {
                ans += codeAndStateToCount.get(cityAndState);
            }
            if (!codeAndStateToCount.containsKey(stateAndCity)) {
                codeAndStateToCount.put(stateAndCity, 0);
            }
            codeAndStateToCount.put(stateAndCity, codeAndStateToCount.get(stateAndCity) + 1);
        }
        pw.println(ans);
        pw.close();
    }
}