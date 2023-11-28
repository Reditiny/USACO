import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class RentalService {
    static int N, M, R;
    // 产奶量
    static List<Integer> milks = new ArrayList<>();
    // 商店 [收购量，收购价]
    static List<List<Integer>> stores = new ArrayList<>();
    // 租金
    static List<Integer> rents = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("rental.in"));
        PrintWriter pw = new PrintWriter("rental.out");
        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            milks.add(Integer.parseInt(reader.readLine()));
        }
        for (int i = 0; i < M; i++) {
            List<Integer> store = new ArrayList<Integer>();
            st = new StringTokenizer(reader.readLine());
            store.add(Integer.parseInt(st.nextToken()));
            store.add(Integer.parseInt(st.nextToken()));
            stores.add(store);
        }
        for (int i = 0; i < R; i++) {
            rents.add(Integer.parseInt(reader.readLine()));
        }
        // 产奶量 商店收购价 邻居租金 均降序排列
        milks.sort((o1, o2) -> o2.compareTo(o1));
        stores.sort((o1, o2) -> o2.get(1).compareTo(o1.get(1)));
        rents.sort((o1, o2) -> o2.compareTo(o1));
        // 考虑应该卖牛奶还是租牛
        int shopAt = 0;
        int rentAt = 0;
        int cowAt = 0;
        long maxMoney = 0;
        while (cowAt < N) {
            int milkMount = milks.get(cowAt);
            int currShop = shopAt;
            int last = 0;
            // 计算这头牛的牛奶能卖多少钱
            int soldMoney = 0;
            while (currShop < M) {
                int soldMount = Math.min(milkMount, stores.get(currShop).get(0));
                soldMoney += stores.get(currShop).get(1) * soldMount;
                milkMount -= soldMount;
                if (milkMount == 0) {
                    last = soldMount;
                    break;
                } else {
                    currShop++;
                }
            }
            // 判断应该租牛还是卖牛奶
            if (rentAt >= R || soldMoney > rents.get(rentAt)) {
                // 卖牛奶
                maxMoney += soldMoney;
                shopAt = currShop;
                if (shopAt < M) { stores.get(shopAt).set(0,stores.get(shopAt).get(0) - last);}
                cowAt++;
            } else {
                // 租牛 所有牛的租价是一样的，所以出租产量最小的那只
                maxMoney += rents.get(rentAt);
                N--;
                rentAt++;
            }
        }
        pw.println(maxMoney);
        pw.close();
    }
}