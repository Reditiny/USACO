#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int main() {
    freopen("shortcut.in", "r", stdin);
    freopen("shortcut.out", "w", stdout);

    int n, m, t;
    cin >> n >> m >> t;

    vector<int> fields(n);
    for (int i = 0; i < n; i++) { cin >> fields[i]; }

    // pair 保存 {time, next_node}
    vector<vector<pair<int, int>>> graph(n);
    for (int i = 0; i < m; i++) {
        int a, b, time;
        cin >> a >> b >> time;
        a--;b--;
        graph[a].push_back({time, b});
        graph[b].push_back({time, a});
    }
    // cost 存储最短路径的距离
    vector<int> dist(n, INT32_MAX);
    // prev 存储最短路径中的前驱节点
    vector<int> prev(n, INT32_MAX);
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> dijkstra;
    dist[0] = 0;
    dijkstra.push({0, 0});
    while (dijkstra.size()) {
        // pair {time, next_node}
        pair<int, int> cur = dijkstra.top();
        dijkstra.pop();
        int cur_cost = cur.first;
        int cur_node = cur.second;
        if (cur_cost != dist[cur_node]) { continue; }
        // 遍历所有相邻的节点
        for (const pair<int, int> &u : graph[cur_node]) {
            // 更新距离
            if (u.first + cur_cost < dist[u.second]) {
                // 距离变小直接更新
                dist[u.second] = u.first + cur_cost;
                dijkstra.push({u.first + cur_cost, u.second});
                prev[u.second] = cur_node;
            } else if (u.first + cur_cost == dist[u.second] && cur_node < prev[u.second]) {
                // 距离相等，但是当前节点编号更小，更新
                prev[u.second] = cur_node;
                dijkstra.push({u.first + cur_cost, u.second});
            }
        }
    }
    // 记录经过 fields[i] 的牛的数量
    // cow_count[i] 为本场地的牛的数量 + 前驱场地的牛的数量
    vector<long long> cow_count(n);
    for (int i = 0; i < n; i++) {
        int cur = i;
        while (cur != INT32_MAX) {
            cow_count[cur] += fields[i];
            cur = prev[cur];
        }
    }
    // 最大节省时间
    // 牛的个数 * 捷径节省的时间
    long long ans = 0;
    for (int i = 0; i < n; i++) {
        ans = max(ans, (long long)(cow_count[i] * (dist[i] - t)));
    }
    cout << ans << '\n';
}
