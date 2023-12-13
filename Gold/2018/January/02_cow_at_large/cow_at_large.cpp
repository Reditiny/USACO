#include<cstdio>
#include<iostream>
#include<queue>
#include<vector>
using namespace std;

const long long MAXN = 1e5, INF = 1e9;
// 保存谷仓的连通关系
vector<long long> graph[MAXN];

int main() {
    freopen("atlarge.in", "r", stdin);
    freopen("atlarge.out", "w", stdout);
    long long n, k;
    cin >> n >> k;
    k--;
    for (int i = 0; i < n - 1; i++) {
        long long a, b;
        cin >> a >> b;
        a--, b--;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    // dist2 表示从牛所在位置到谷仓 i 的最短路径
    // dist1 表示从任何给定农民位置到谷仓 i 的最短路径
    vector<long long> dist1(n, INF), dist2(n, INF);
    dist2[k] = 0;
    queue<long long> q;
    q.push(k);
    // BFS 计算 k 到其他点的最短路径
    while (!q.empty()) {
        long long cur_barn = q.front();
        q.pop();
        for (long long next_barn : graph[cur_barn]) {
            if (dist2[cur_barn] + 1 < dist2[next_barn]) {
                dist2[next_barn] = dist2[cur_barn] + 1;
                q.push(next_barn);
            }
        }
    }
    // 找到所有只有一条路的节点，即农民的位置
    for (int i = 0; i < n; i++) {
        if (graph[i].size() == 1) {
            q.push(i);
            dist1[i] = 0;
        }
    }
    // BFS 计算农民到其他点的最短路径
    while (!q.empty()) {
        long long cur = q.front();
        q.pop();
        for (long long u : graph[cur]) {
            if (dist1[cur] + 1 < dist1[u]) {
                dist1[u] = dist1[cur] + 1;
                q.push(u);
            }
        }
    }

    long long ans = 0;
    q.push(k);
    vector<bool> visited(n);
    while (!q.empty()) {
        long long cur = q.front();
        q.pop();
        if (dist1[cur] <= dist2[cur]) {
            // 此时说明有个农民可以在 cur 处拦截牛
            // 那么这个农民就是必须的
            ans++;
            continue;
        }
        if (visited[cur]) { continue; }
        visited[cur] = true;
        for (long long u : graph[cur]) { q.push(u); }
    }
    cout << ans << endl;
}