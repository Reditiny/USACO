#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;

int main() {
    freopen("pails.in", "r", stdin);
    freopen("pails.out", "w", stdout);

    int X, Y, M;
    cin >> X >> Y >> M;

    int maxX = M / X;
    int ans = 0;

    // 搜索范围内遍历得到最大值
    for (int i = 0; i <= maxX; i++) {
        // 对于给定的 i 个 x 确定 y 的数量为 (m - x * i) / y
        int j = (M - X * i) / Y;
        ans = max(ans, X * i + Y * j);
    }

    cout << ans << endl;

    return 0;
}
