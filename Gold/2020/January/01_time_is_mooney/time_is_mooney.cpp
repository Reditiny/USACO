# include <iostream>
# include <vector>
using namespace std;

const int MAX_DAYS = 1000;

int main() {
    freopen("time.in", "r", stdin);
    freopen("time.out", "w", stdout);

    int n, m, c;
    cin >> n >> m >> c;
    vector<int> moonies(n);
    for (int i = 0; i < n; i++) { cin >> moonies[i]; }
    vector<vector<int>> graph(n);
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        graph[--u].push_back(--v);
    }
    // dp[d][i] 表示第 d+1 天在 i 城市的最大收益
    // 状态转移方程
    // dp[d + 1][u] = max(dp[d + 1][u], dp[d][i] + moonies[u]) for u in graph[i]
    // 依次遍历所有城市 i 可以到达的城市 u
    vector<vector<int>> dp(MAX_DAYS + 1, vector<int>(n, -1));
    dp[0][0] = 0;
    int ans = 0;
    for (int d = 0; d < MAX_DAYS; d++) {
        for (int i = 0; i < n; i++) {
            // dp[d][i] == -1 表示第 d 天在 i 城市不可达
            if (dp[d][i] != -1) {
                // 第 d 天从城市 i 出发，d + 1 天可以到达的城市 u
                // 更新所有的 dp[d + 1][u]
                for (int u : graph[i]) {
                    dp[d + 1][u] = max(dp[d + 1][u], dp[d][i] + moonies[u]);
                }
            }
        }
        ans = max(ans, (dp[d][0] - (c * d * d)));
    }
    cout << ans << "\n";
}