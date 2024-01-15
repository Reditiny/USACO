#include <iostream>
#include <fstream>
#include <vector>
#include <climits>

using namespace std;

int min_x = INT_MAX, second_min_x = INT_MAX, second_max_x = 0, max_x = 0;
int min_y = INT_MAX, second_min_y = INT_MAX, second_max_y = 0, max_y = 0;
int n;
int ans;
// 更新最大次大最小次小值
void update_min_max(int x, int y) {
    if (x < min_x) {
        second_min_x = min_x;
        min_x = x;
    } else if (x < second_min_x) {
        second_min_x = x;
    }
    if (x > max_x) {
        second_max_x = max_x;
        max_x = x;
    } else if (x > second_max_x) {
        second_max_x = x;
    }

    if (y < min_y) {
        second_min_y = min_y;
        min_y = y;
    } else if (y < second_min_y) {
        second_min_y = y;
    }
    if (y > max_y) {
        second_max_y = max_y;
        max_y = y;
    } else if (y > second_max_y) {
        second_max_y = y;
    }
}

int main() {
    ifstream r("reduce.in");
    ofstream pw("reduce.out");

    r >> n;
    vector<int> x(n);
    vector<int> y(n);

    for (int i = 0; i < n; i++) {
        r >> x[i] >> y[i];
        update_min_max(x[i], y[i]);
    }
    ans = (max_x - min_x) * (max_y - min_y);
    // 遍历每个点，得到去除该点后的最小面积
    // 因为维护了最大次大最小次小值，所以求面积的复杂度为O(1)
    for (int i = 0; i < n; i++) {
        int cur_min_x = min_x;
        if (x[i] == cur_min_x) {
            cur_min_x = second_min_x;
        }
        int cur_max_x = max_x;
        if (x[i] == cur_max_x) {
            cur_max_x = second_max_x;
        }
        int cur_min_y = min_y;
        if (y[i] == cur_min_y) {
            cur_min_y = second_min_y;
        }
        int cur_max_y = max_y;
        if (y[i] == cur_max_y) {
            cur_max_y = second_max_y;
        }
        ans = min(ans, (cur_max_x - cur_min_x) * (cur_max_y - cur_min_y));
    }

    pw << ans << endl;
    pw.close();

    return 0;
}
