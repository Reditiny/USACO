#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

// Disjoint Set Union 并查集
struct DSU {
    // e[i] < 0 表示 i 是根节点，且大小为 -e[i]
    // e[i] >= 0 表示 i 的父节点是 e[i]
    vector<int> e;
    DSU(int N) : e(N, -1) {}
    // 获取 x 的根节点，同时进行路径压缩
    int get(int x) { return e[x] < 0 ? x : e[x] = get(e[x]); }
    // 判断两个节点是否在同一个集合中，即是否有相同的根节点
    bool same_set(int a, int b) { return get(a) == get(b); }
    // 获取 x 所在集合的大小
    int size(int x) { return -e[get(x)]; }
    // 合并两个集合 返回 false 表示两个集合本来就在一个集合中
    bool unite(int x, int y) {
        x = get(x), y = get(y);
        if (x == y) return false;
        if (e[x] > e[y]) swap(x, y);
        e[x] += e[y];
        e[y] = x;
        return true;
    }
};

int main() {
    freopen("closing.in", "r", stdin);
    freopen("closing.out", "w", stdout);
    int n, m;
    cin >> n >> m;
    // 邻接表保存图
    vector<vector<int>> adj(n);
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        u--;
        v--;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    // isOpen[i] 表示第 i 个节点是否打开
    vector<bool> isOpen(n);
    vector<int> order(n);
    for (int i = 0; i < n; i++) {
        cin >> order[i];
        order[i]--;
    }
    DSU dsu(n);
    // 从前往后依次关闭牧场，等价于从后往前依次打开牧场
    reverse(order.begin(), order.end());
    isOpen[order[0]] = 1;
    vector<string> ans = {"YES"};
    int cc = 1;
    for (int i = 1; i < n; i++) {
        cc++;
        // 每次打开一个节点
        isOpen[order[i]] = 1;
        for (int j : adj[order[i]]) {
            // 如果相邻的节点已经打开，那么合并两个集合
            if (isOpen[j]) {
                if (dsu.unite(j, order[i])) { cc--; }
            }
        }
        ans.push_back(cc == 1 ? "YES" : "NO");
    }
    reverse(ans.begin(), ans.end());
    for (const string &i : ans) { cout << i << '\n'; }
}