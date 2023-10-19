import java.io.BufferedReader;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @author Red
 * @version 1.0
 */
public class StuckInRut {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        List<Cow> nCows = new ArrayList<>();
        List<Cow> eCows = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] s = scanner.nextLine().split(" ");
            Cow cow = new Cow(i, Integer.parseInt(s[1]), Integer.parseInt(s[2]));
            if ("N".equals(s[0])) {
                nCows.add(cow);
            } else {
                eCows.add(cow);
            }
        }
        // 向北的牛按横坐标从小到大排序，向东的牛按纵坐标从小到大排序
        nCows.sort(Comparator.comparingInt(o -> o.x));
        eCows.sort(Comparator.comparingInt(o -> o.y));
        // 向东的牛只有可能被横坐标更大的向北的牛阻挡，向北的牛只有可能被纵坐标更大的向东的牛阻挡
        for (Cow ncow : nCows) {
            for (Cow ecow : eCows) {
                // 检查两牛e和n路径是否会相交，此处要注意e/n牛可能被其他牛阻挡导致两者路径并不会相交的情况
                if (ecow.y > ncow.y && ecow.x < ncow.x && (ecow.stopAt == -1 || ecow.stopAt > ncow.x) && (ncow.stopAt == -1 || ncow.stopAt > ecow.y)) {
                    int diffX = ncow.x - ecow.x;
                    int diffY = ecow.y - ncow.y;
                    if (diffX > diffY && ecow.stopAt == -1) {
                        ecow.moveDistance = diffX;
                        ecow.stopAt = ncow.x;
                    }
                    if (diffX < diffY && ncow.stopAt == -1) {
                        ncow.moveDistance = diffY;
                        ncow.stopAt = ecow.y;
                        break;
                    }
                }
            }
        }
        eCows.addAll(nCows);
        // 按照牛的输入顺序来输出
        eCows.sort(Comparator.comparingInt(o -> o.order));
        eCows.forEach(cow -> {
            if (cow.stopAt == -1) {
                System.out.println("Infinity");
            } else {
                System.out.println(cow.moveDistance);
            }
        });
    }
}

class Cow {
    int order;  // 记录牛的顺序 用于输出
    int x;
    int y;
    int stopAt; // 记录牛被阻挡的位置 用于检查路径相交的逻辑
    int moveDistance; // 记录牛被阻挡时移动的距离

    public Cow(int order, int x, int y) {
        this.order = order;
        this.x = x;
        this.y = y;
        this.stopAt = -1;
    }
}
