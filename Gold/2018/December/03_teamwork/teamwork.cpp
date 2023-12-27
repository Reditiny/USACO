#include <iostream>
#include <vector>
using namespace std;

int main() {
    freopen("teamwork.in", "r", stdin);
    freopen("teamwork.out", "w", stdout);

    int n, k;
    cin >> n >> k;
    vector<int> skills(n);
    for (int i = 0; i < n; i++) { cin >> skills[i]; }

    // dp[i] 表示前 i 个 cow 的最大 skill 和
    // 状态转移方程
    // dp[i] = max(dp[i], dp[j - 1] + (cur * (i - j + 1))) for j in [i - k + 1, i]
    vector<int> dp(n, -1);
    for (int i = 0; i < n; i++) {
        // 当前的 i，选择前面 k 个 cow 中最大 skill 的 cow 组队
        int cur = skills[i];
        for (int j = i; j >= (i - k + 1) && j >= 0; j--) {
            cur = max(cur, skills[j]);
            if (j > 0) {
                dp[i] = max(dp[i], dp[j - 1] + (cur * (i - j + 1)));
            } else {
                dp[i] = max(dp[i], cur * (i - j + 1));
            }
        }
    }
    cout << dp[n - 1] << endl;
}
