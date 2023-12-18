#include <iostream>
#include <vector>
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

// 边 u v 为两个端点 w 为权重
struct node {
    int u, v, w;
};
// 查询 idx 表示第 idx 个查询，v 表示查询的节点，k 表示查询的 k
struct query {
    int idx, v, k;
};

bool edgeCmp(node &a, node &b) {
    return a.w > b.w;
}

bool queryCmp(query &a, query &b) {
    return a.k > b.k;
}

int main() {
    freopen("mootube.in", "r", stdin);
    freopen("mootube.out", "w", stdout);

    int n, q;
    cin >> n >> q;
    vector<node> edges(n - 1);
    for (int i = 0; i < n - 1; i++) {
        int u, v, w;
        cin >> u >> v >> w;
        u--;
        v--;
        edges[i] = {u, v, w};
    }
    vector<query> queries(q);
    for (int i = 0; i < q; i++) {
        int v, k;
        cin >> k >> v;
        v--;
        queries[i] = {i, v, k};
    }
    // 按照 queries 的 k 从大到小排序
    sort(queries.begin(), queries.end(), queryCmp);
    // 按照 edges 的权重从大到小排序
    sort(edges.begin(), edges.end(), edgeCmp);

    DSU dsu(n);
    vector<int> ans(q);
    int idx = 0;
    // 从大到小依次处理 queries
    for (auto query: queries) {
        int v = query.v;
        int curK = query.k;
        // 将所有权重大于等于 curK 的边加入并查集
        while (idx < (int) edges.size() && edges[idx].w >= curK) {
            dsu.unite(edges[idx].u, edges[idx].v);
            idx++;
        }
        ans[query.idx] = dsu.size(v) - 1;
    }

    for (auto x: ans) { cout << x << '\n'; }
}
