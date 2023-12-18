#include <vector>
#include <iostream>
#include <algorithm>
typedef long long ll;
using namespace std;

// 存储本次挤奶和距离上次挤奶的天数
struct node {
    int session, days;
};
// 拓扑排序 深度优先遍历节点 然后将节点加入排序结果
// 最后得到的 toposort 就是拓扑排序的逆序
void topo_sorting(vector<vector<node > > &graph, vector<bool> &visited,
                  vector<int> &toposort, int session) {
    visited[session] = true;
    for (auto i : graph[session]) {
        int a = i.session;
        if (visited[a]) { continue; }
        topo_sorting(graph, visited, toposort, a);
    }
    toposort.push_back(session);
}

int main() {
    freopen("timeline.in", "r", stdin);
    freopen("timeline.out", "w", stdout);
    int n, m, c;
    cin >> n >> m >> c;
    vector<int> start(n + 1);
    for (int i = 1; i <= n; i++) { cin >> start[i]; }

    vector<vector<node > > graph(n + 1);
    for (int i = 0; i < c; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        graph[a].push_back({b, c});
    }
    // 从最早的挤奶阶段开始，对挤奶阶段进行拓扑排序。
    vector<bool> visited(n + 1);
    vector<int> toposort;
    for (int i = 1; i <= n; i++) {
        if (visited[i]) { continue; }
        topo_sorting(graph, visited, toposort, i);
    }
    // 从后往前遍历，因为 topo_sorting 得到的是拓扑排序的逆序
    // 贪心地将所有之后挤奶的时间至少设置为本次挤奶的时间+天数。
    for (int i = n - 1; i >= 0; i--) {
        for (auto j : graph[toposort[i]]) {
            int a = j.session, b = j.days;
            start[a] = max(start[a], start[toposort[i]] + b);
        }
    }
    for (int i = 1; i <= n; i++) { cout << start[i] << "\n"; }

    return 0;
}