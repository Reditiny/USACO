#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <climits>
using namespace std;
// 边的数据结构
struct Edge {
    int a, time;
    Edge(int other, int time) : a(other), time(time) {}
};
// 优先队列中的数据结构
struct State {
    int pos, time;
    int eaten;
    State(int pos, int time, int eaten) : pos(pos), time(time), eaten(eaten) {}
    // 重载 < 运算符使得 priority_queue 能够正常工作
    // 优先让没有吃过草堆的 cow 先吃草堆，然后按照时间从小到大排序
    bool operator<(const State& other) const {
        if (eaten != other.eaten) return eaten > other.eaten;
        return time > other.time;
    }
};

int main() {
    freopen("dining.in", "r", stdin);
    freopen("dining.out", "w", stdout);
    int n, m, k;
    cin >> n >> m >> k;
    // 邻接表保存图
    vector<vector<Edge>> graph(n, vector<Edge>());
    for (int i = 0; i < m; i++) {
        int a, b, time;
        cin >> a >> b >> time;
        a--; b--;
        graph[a].push_back(Edge(b, time));
        graph[b].push_back(Edge(a, time));
    }
    // 草堆
    vector<int> hay_bales(n, -1);
    for (int i = 0; i < k; i++) {
        int index, yumminess;
        cin >> index >> yumminess;
        hay_bales[index - 1] = yumminess;  // 0-based indexing
    }
    // dist[i][0] 存储从 cow i 到 barn 的最短距离，且 cow i 没有吃过草堆
    // dist[i][1] 存储从 cow i 到 barn 的最短距离，且 cow i 吃过草堆
    vector<vector<int>> dist(n, vector<int>(2, -1));

    // dijkstra 计算最短距离
    // 此处使用了自定义的数据类型 State，要求重载 < 运算符
    priority_queue<State> dijkstra;
    dijkstra.push(State(n - 1, 0, 0));

    while (!dijkstra.empty()) {
        State state = dijkstra.top();
        dijkstra.pop();
        if (dist[state.pos][state.eaten] != -1) continue;
        dist[state.pos][state.eaten] = state.time;
        // 将所有相邻的节点入队
        for (const Edge& e : graph[state.pos]) {
            dijkstra.push(State(e.a, state.time + e.time, state.eaten));
        }
        // 如果当前节点有草堆，且 cow 还没有吃过草堆，那么吃掉草堆，更新状态
        if (hay_bales[state.pos] != -1 && state.eaten == 0) {
            state.time -= hay_bales[state.pos];
            dijkstra.push(State(state.pos, state.time, 1));
        }
    }

    for (int i = 0; i < n - 1; i++) {
        // dist[i][1] 更小，那么 cow i 可以吃到草
        if (dist[i][1] <= dist[i][0]) {
            cout << 1 << endl;
        } else {
            cout << 0 << endl;
        }
    }
    return 0;
}