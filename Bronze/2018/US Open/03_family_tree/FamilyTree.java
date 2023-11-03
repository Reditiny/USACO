import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class FamilyTree {
    static Map<String, String> cowToMotherCow = new HashMap<String, String>();
    static List<String> cow1Ancestors = new ArrayList<String>();
    static List<String> cow2Ancestors = new ArrayList<String>();

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("family.in"));
        PrintWriter pw = new PrintWriter("family.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        int n = Integer.parseInt(st.nextToken());
        String cow1 = st.nextToken();
        String cow2 = st.nextToken();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(r.readLine());
            String motherCow = st.nextToken();
            String cow = st.nextToken();
            // 记录子牛->母牛的映射关系 便于后续寻找祖先
            cowToMotherCow.put(cow, motherCow);
        }
        // 从 cow1 开始依次记录祖先(包括自己)
        String curCow = cow1;
        cow1Ancestors.add(cow1);
        while (cowToMotherCow.containsKey(curCow)) {
            String mother = cowToMotherCow.get(curCow);
            cow1Ancestors.add(mother);
            curCow = mother;
        }
        // 从 cow2 开始依次记录祖先(包括自己)
        curCow = cow2;
        cow2Ancestors.add(cow2);
        while (cowToMotherCow.containsKey(curCow)) {
            String mother = cowToMotherCow.get(curCow);
            cow2Ancestors.add(mother);
            curCow = mother;
        }
        // 检查是否有共同祖先并记录距离的代数
        int cow1Step = 0, cow2Step = 0;
        for (int i = 0; i < cow1Ancestors.size(); i++) {
            if (cow2Ancestors.contains(cow1Ancestors.get(i))) {
                cow1Step = i;
                cow2Step = cow2Ancestors.indexOf(cow1Ancestors.get(i));
                break;
            }
        }
        // 根据代数关系输出结果
        if (cow1Step == 0 && cow2Step == 0) {
            // 1.没有共同祖先说明没有关系
            pw.println("NOT RELATED");
        } else if (cow1Step >= 2 && cow2Step >= 2) {
            // 2.两者离共同祖先都相距很远
            pw.println("COUSINS");
        } else if (cow1Step == 1 && cow2Step == 1) {
            // 3.同一个妈妈
            pw.println("SIBLINGS");
        } else if (cow1Step == 0 || cow2Step == 0) {
            // 4.直接祖先
            // 先确定谁是祖先
            String ancestorCow, cow;
            if (cow1Step == 0) {
                ancestorCow = cow1;
                cow = cow2;
            } else {
                ancestorCow = cow2;
                cow = cow1;
            }
            // 根据相隔的代数输出结果
            int diff = Math.abs(cow1Step - cow2Step);
            pw.print(ancestorCow + " is the ");
            while (diff >= 3) {
                pw.print("great-");
                diff--;
            }
            while (diff >= 2) {
                pw.print("grand-");
                diff--;
            }
            pw.println("mother of " + cow);
        } else {
            // 5.长少关系
            // 确定谁是长辈
            String ancestorCow, cow;
            if (cow1Step < cow2Step) {
                ancestorCow = cow1;
                cow = cow2;
            } else {
                ancestorCow = cow2;
                cow = cow1;
            }
            // 根据相隔的代数输出结果
            int diff = Math.abs(cow1Step - cow2Step);
            pw.print(ancestorCow + " is the ");
            while (diff >= 2) {
                pw.print("great-");
                diff--;
            }
            pw.println("aunt of " + cow);
        }
        pw.close();
    }
}
