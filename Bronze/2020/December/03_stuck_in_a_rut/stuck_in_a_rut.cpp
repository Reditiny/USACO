#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class Cow {
public:
    int order;  // 记录牛的顺序 用于输出
    int x;
    int y;
    int stop_at;  // 记录牛被阻挡的位置 用于检查路径相交的逻辑
    int move_distance;   // 记录牛被阻挡时移动的距离
    Cow(int order, int x, int y) : order(order), x(x), y(y), stop_at(-1), move_distance(0) {}
};

int main() {
    int n;
    cin >> n;
    // 忽略一行
    cin.ignore();

    vector<Cow> nCows, eCows;
    for (int i = 0; i < n; i++) {
        char direction;
        int x, y;
        cin >> direction >> x >> y;

        Cow cow(i, x, y);
        if (direction == 'N') {
            nCows.push_back(cow);
        } else {
            eCows.push_back(cow);
        }
    }

    // 向北的牛按横坐标从小到大排序，向东的牛按纵坐标从小到大排序
    sort(nCows.begin(), nCows.end(), [](const Cow &a, const Cow &b) {
        return a.x < b.x;
    });
    sort(eCows.begin(), eCows.end(), [](const Cow &a, const Cow &b) {
        return a.y < b.y;
    });
    // 向东的牛只有可能被横坐标更大的向北的牛阻挡，向北的牛只有可能被纵坐标更大的向东的牛阻挡
    for (Cow &ncow: nCows) {
        for (Cow &ecow: eCows) {
            // 检查两牛e和n路径是否会相交，此处要注意e/n牛可能被其他牛阻挡导致两者路径并不会相交的情况
            if (ecow.y > ncow.y && ecow.x < ncow.x && (ecow.stop_at == -1 || ecow.stop_at > ncow.x) &&
                (ncow.stop_at == -1 || ncow.stop_at > ecow.y)) {
                int diffX = ncow.x - ecow.x;
                int diffY = ecow.y - ncow.y;
                if (diffX > diffY && ecow.stop_at == -1) {
                    ecow.move_distance = diffX;
                    ecow.stop_at = ncow.x;
                }
                if (diffX < diffY && ncow.stop_at == -1) {
                    ncow.move_distance = diffY;
                    ncow.stop_at = ecow.y;
                    break;
                }
            }
        }
    }
    // 按照牛的输入顺序来输出
    eCows.insert(eCows.end(), nCows.begin(), nCows.end());
    sort(eCows.begin(), eCows.end(), [](const Cow &a, const Cow &b) {
        return a.order < b.order;
    });

    for (const Cow &cow: eCows) {
        if (cow.stop_at == -1) {
            cout << "Infinity" << endl;
        } else {
            cout << cow.move_distance << endl;
        }
    }

    return 0;
}
