#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> cows;

int explode_number(int start);
int explode_right(int start, int radius);
int explode_left(int start, int radius);

int main() {
    freopen("angry.in", "r", stdin);
    freopen("angry.out", "w", stdout);

    int n;
    cin >> n;

    cows.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> cows[i];
    }

    sort(cows.begin(), cows.end());

    int ans = 0;
    for (int i = 0; i < n; i++) {
        ans = max(ans, explode_number(i));
    }

    cout << ans << endl;

    return 0;
}
// 计算从 start 开始爆炸最终有多少草包爆炸
int explode_number(int start) {
    return 1 + explode_left(start, 1) + explode_right(start, 1);
}
/**
 * 递归地计算右边爆炸多少草包 因为任意起点左右两边的爆炸互不干扰所以分开计算
 * 本层递归只计算本次爆炸所引爆的草包数 并找到最远的草包的位置
 * 下层递归以该草包为爆炸起点
 */
int explode_right(int start, int radius) {
    int ans = 0;
    int right = start;
    for (int i = start + 1; i < cows.size(); i++) {
        if (cows[i] - cows[start] <= radius) {
            ans++;
            right = i;
        } else {
            break;
        }
    }

    if (right == start) {
        return 0;
    }

    return ans + explode_right(right, radius + 1);
}

int explode_left(int start, int radius) {
    int ans = 0;
    int left = start;
    for (int i = start - 1; i >= 0; i--) {
        if (cows[start] - cows[i] <= radius) {
            ans++;
            left = i;
        } else {
            break;
        }
    }

    if (left == start) {
        return 0;
    }

    return ans + explode_left(left, radius + 1);
}
