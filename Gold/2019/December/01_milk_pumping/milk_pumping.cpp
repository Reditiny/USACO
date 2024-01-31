#include <iostream>
#include <vector>
#include <queue>
using namespace std;

struct Edge {
    int a, b, flow;
};
int n, m;
const int PRECISION = 1e6;
vector<vector<Edge>> adj;
int dijkstra(int min_flow);


int main() {
    freopen("pump.in", "r", stdin);
    freopen("pump.out", "w", stdout);
    cin >> n >> m;
    adj.resize(n);
    vector<int> flows;
    for (int i = 0; i < m; i++) {
        int a, b, c, f;
        cin >> a >> b >> c >> f;
        a--, b--;
        flows.push_back(f);
        adj[a].push_back({b, c, f});
        adj[b].push_back({a, c, f});
    }

    int ans = -1;
    // 遍历所有的流量
    for (int flow : flows) {
        int cur = dijkstra(flow);
        // 没有路径
        if (cur == -1) { continue; }
        double ratio = double(flow) / double(cur);
        ans = max(ans, (int)(ratio * PRECISION));
    }
    cout << ans << '\n';
    return 0;
}
// dijkstra's 算法计算最短路径 返回最短路径的 cost
// 把 cost 当作距离，计算最短路径
// 流量大于 min_flow 的边才能被考虑
// dijkstra's 算法只适合计算单源最短路径，且边的权重必须非负，算法过程如下：
// 1. 从起点开始，起点到起点的距离为 0，其他点到起点的距离为无穷大
// 2. 从所有的点中选择一个距离最短的点，将这个点标记为已访问
// 3. 对于这个点的所有相邻的点，如果到起点的距离可以变小则更新这些点到起点的距离
// 4. 重复 2 和 3 直到所有的点都被访问
int dijkstra(int min_flow) {
    // pair<节点号, 节点分数>
    priority_queue<pair<int, int>> pq;
    vector<int> cost(n, INT32_MAX);
    vector<bool> visited(n, false);

    cost[0] = 0;
    pq.push({0, 0});

    while (!pq.empty()) {
        pair<int, int> next = pq.top();
        pq.pop();
        if (next.second != cost[next.first] || visited[next.first]) { continue; }
        for (Edge u : adj[next.first]) {
            int ct = u.b + next.second;
            // 流量不够或者已经访问过
            if (u.flow < min_flow || visited[u.a]) { continue; }
            // 更新最短路径
            if (cost[u.a] > ct) {
                cost[u.a] = ct;
                pq.push({u.a, ct});
            }
        }
    }
    return cost[n - 1] == INT32_MAX ? -1 : cost[n - 1];
}