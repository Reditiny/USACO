import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class CowEvolution {
    static List<Set<String>> cows;
    static Set<String> features;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("evolution.in"));
        PrintWriter pw = new PrintWriter("evolution.out");
        int n = Integer.parseInt(r.readLine());
        cows = new ArrayList<>();
        features = new HashSet<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            int k = Integer.parseInt(st.nextToken());
            Set<String> cow = new HashSet<>();
            for (int j = 0; j < k; j++) {
                cow.add(st.nextToken());
            }
            features.addAll(cow);
            cows.add(cow);
        }
        // HashSet为保证唯一 List方便遍历
        List<String> featureList = new ArrayList<>(features);

        // 遍历所有两两(i,j)特征组合
        for (int i = 0; i < featureList.size(); i++) {
            for (int j = i + 1; j < featureList.size(); j++) {
                // 是否有牛同时具有(i,j)特征 是否有牛只具备i特征 是否有牛只具备j特征
                boolean both = false, iOnly = false, jOnly = false;
                for (Set<String> cow : cows) {
                    boolean iFeature = cow.contains(featureList.get(i));
                    boolean jFeature = cow.contains(featureList.get(j));
                    if (iFeature && jFeature) {
                        both = true;
                    } else if (iFeature) {
                        iOnly = true;
                    } else if (jFeature) {
                        jOnly = true;
                    }
                }
                // iOnly 说明 i 特征是某次进化的特征
                // jOnly 说明 j 特征是某次进化的特征
                // both 说明 i 特征和 j 特征是同一支牛先后进化出的特征
                // 正常的树不会出现三者同为真
                if (iOnly && jOnly && both) {
                    pw.println("no");
                    pw.close();
                    return;
                }
            }
        }
        pw.println("yes");
        pw.close();
    }
}
