#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>

using namespace std;

int main() {
    freopen("hps.in", "r", stdin);
    freopen("hps.out", "w", stdout);

    int n, k;
    cin >> n >> k;
    vector<int> sequence(n + 1);

    for (int x = 1; x <= n; x++) {
        char c;
        cin >> c;
        if (c == 'H') {
            sequence[x] = 0;
        } else if (c == 'P') {
            sequence[x] = 1;
        } else {
            sequence[x] = 2;
        }
    }

    int maximum = 0;
    vector<vector<vector<int> > > dp(n + 1, vector<vector<int> >(k + 2, vector<int>(3)));
    // dp[i][j][s] 表示前 i 个数中，第 j 次改变，且第 i 个数被改变为 s 时的最大值
    // 以 dp[i][j][0] 为例 add1 表示本次手势为 0 时是否加分
    // 1. 手势没变，上次手势也是 0，且 j 不变，dp[i][j][0] = dp[i - 1][j][0] + (sequence[i] == 0 ? 1 : 0)
    // 2. 手势改变，上次手势是 1，且 j - 1 ，dp[i][j][0] = dp[i - 1][j - 1][1] + (sequence[i] == 0 ? 1 : 0)
    // 3. 手势改变，上次手势是 2，且 j - 1 ，dp[i][j][0] = dp[i - 1][j - 1][2] + (sequence[i] == 0 ? 1 : 0)
    // 得状态转移方程 dp[i][j[0] = max(dp[i - 1][j][0],dp[i - 1][j - 1][1],dp[i - 1][j - 1][2]) + (sequence[i] == 0 ? 1 : 0)
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= k + 1; j++) {
            for (int s = 0; s < 3; s++) {
                int add = sequence[i] == s ? 1 : 0;
                dp[i][j][s] = max(dp[i - 1][j][s], max(dp[i - 1][j - 1][(s + 1) % 3], dp[i - 1][j - 1][(s + 2) % 3])) + add;
                if (i == n) {
                   maximum = max(maximum,dp[n][j][s]);
                }
            }
        }
    }
    // 空间复杂度上还可以进一步优化，可以将 dp[i][j][s] 降维成 dp[j][s]
    // 因为 dp[j][s] 在赋值之前表示的是 dp[i - 1][j][s]
    // 而赋值之后表示的是 dp[i][j][s]
    // 由此即使数组中不存在 i 这个维度，也可以通过 j 和 s 来确定 dp[i][j][s]
    // 优化后的代码如下，也可以通过所有案例
    //    vector<vector<int> > dp(vector<vector<int> >(k + 2, vector<int>(3)));
    //    for (int i = 1; i <= n; i++) {
    //        for (int j = 1; j <= k + 1; j++) {
    //            for (int s = 0; s < 3; s++) {
    //                int add = sequence[i] == s ? 1 : 0;
    //                dp[j][s] = max(dp[j][s], max(dp[j - 1][(s + 1) % 3], dp[j - 1][(s + 2) % 3])) + add;
    //                if (i == n) {
    //                   maximum = max(maximum,dp[j][s]);
    //                }
    //            }
    //        }
    //    }
    cout << maximum << endl;
    return 0;
}
