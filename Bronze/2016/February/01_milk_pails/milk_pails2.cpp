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
    int maxY = M / Y;
    int ans = 0;

    // 搜索范围内遍历得到最大值
    for (int i = 0; i <= maxX; i++) {
        for (int j = 0; j <= maxY; j++) {
            if (X * i + Y * j <= M) {
                ans = max(ans, X * i + Y * j);
            }
        }
    }

    cout << ans << endl;

    return 0;
}
