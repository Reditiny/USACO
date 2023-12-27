#include <iostream>
#include <vector>
using namespace std;

int main() {
    freopen("feast.in", "r", stdin);
    freopen("feast.out", "w", stdout);
    int T, A, B;
    cin >> T >> A >> B;
    // dp[i][j] 表示喝 i 次水，饱腹感达到 j 的可能性
    // 状态转移方程：
    // dp[i][j] = dp[i][j-A] | dp[i][j-B] | dp[i-1][j/2]
    vector<vector<bool> > dp(2, vector<bool>(T));
    dp[0][0] = true;
    for (int i = 0; i < T + 1; ++i) {
        // 吃橘子
        if (i - A >= 0) dp[0][i] = dp[0][i] | dp[0][i - A];
        // 吃柠檬
        if (i - B >= 0) dp[0][i] = dp[0][i] | dp[0][i - B];
        // 第一次喝水
        dp[1][i / 2] = dp[1][i / 2] | dp[0][i];
    }
    for (int i = 0; i < T + 1; ++i) {
        // 吃橘子
        if (i - A >= 0) dp[1][i] = dp[1][i] | dp[1][i - A];
        // 吃柠檬
        if (i - B >= 0) dp[1][i] = dp[1][i] | dp[1][i - B];
    }
    // 找到最大的饱腹感
    for (int i = T; i >= 0; --i)
        if (dp[1][i]) {
            cout << i;
            break;
        }
    return 0;
}