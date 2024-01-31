#include <algorithm>
#include <iostream>
#include <vector>
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
struct Edge {
    int from, to, b;
    bool operator<(const Edge &e) const { return b < e.b; }
};

int main() {
    int n;
    cin >> n;
    DSU dsu(n * 2);
    int cost, p1, p2, p3, p4;
    vector<Edge> edges;
    for (int i = 0; i < n; i++) {
        cin >> cost >> p1 >> p2 >> p3 >> p4;
        // p1 到 p2 和 p3 到 p4 可以直接切换
        edges.push_back({p1 - 1, p2 - 1, 0});
        edges.push_back({p3 - 1, p4 - 1, 0});
        // p1 到 p3 则需要花费 cost 进行切换
        // p2 到 p4 的边不需要添加，因为 p2 - p1 - p3 - p4 这条路径已经包含了 p2 - p4 且费用也是 cost
        edges.push_back({p1 - 1, p3 - 1, cost});
    }
    // Kruskal 算法
    sort(edges.begin(), edges.end());
    int min_cost = 0;
    for (Edge edge : edges) {
        if (dsu.unite(edge.from, edge.to)) { min_cost += edge.b; }
    }
    cout << min_cost << endl;
}