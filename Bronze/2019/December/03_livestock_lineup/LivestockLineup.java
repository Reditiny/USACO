import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Red
 * @version 1.0
 */
public class LivestockLineup {
    static List<List<String>> nears = new ArrayList<List<String>>();
    static String[] cows = new String[]{"Beatrice", "Belinda", "Bella", "Bessie", "Betsy", "Blue", "Buttercup", "Sue"};
    static boolean valid;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("lineup.in"));
        PrintWriter pw = new PrintWriter("lineup.out");
        int n = Integer.parseInt(r.readLine());
        for (int i = 0; i < n; i++) {
            String[] strings = r.readLine().split(" ");
            List<String> cur = new ArrayList<String>();
            cur.add(strings[0]);
            cur.add(strings[5]);
            nears.add(cur);
        }
        List<List<String>> orders = new ArrayList<List<String>>();
        List<String> cur = new ArrayList<String>();
        buildOrders(orders, cur);
        // 因为在 cows 构造时就已经满足字典序了 第一个满足条件的order即是字典序最小的
        for (List<String> order : orders) {
            if (valid(order)) {
                cur = order;
                break;
            }
        }
        for (String cow : cur) {
            pw.println(cow);
        }
        pw.close();
    }

    /**
     * 递归遍历得到所有顺序
     */
    public static void buildOrders(List<List<String>> orders, List<String> cur) {
        if (cur.size() == cows.size()) {
            List<String> copy = new ArrayList<String>(cur);
            orders.add(copy);
            return;
        }
        for (String cow : cows) {
            if (!cur.contains(cow)) {
                cur.add(cow);
                buildOrders(orders, cur);
                cur.remove(cow);
            }
        }
    }

    /**
     * 检查当前顺序是否符合要求
     */
    public static boolean valid(List<String> order) {
        valid = true;
        nears.forEach(near -> {
            if (Math.abs(order.indexOf(near.get(0)) - order.indexOf(near.get(1))) > 1) {
                valid = false;
            }
        });
        return valid;
    }

}
