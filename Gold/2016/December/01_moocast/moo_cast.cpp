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

struct Edge {
    int a, b, dist;
};

bool cmp(const Edge &e1, const Edge &e2) {
    return e1.dist < e2.dist;
}

int main() {
    freopen("moocast.in", "r", stdin);
    freopen("moocast.out", "w", stdout);

    int n;
    cin >> n;

    vector<int> x(n);
    vector<int> y(n);

    for (int i = 0; i < n; i++) { cin >> x[i] >> y[i]; }

    // 每对牛之间都有一条边，边的权重为两个牛之间的距离的平方
    vector<Edge> edges;
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            int dx = x[i] - x[j];
            int dy = y[i] - y[j];
            edges.push_back({i, j, dx * dx + dy * dy});
        }
    }
    // 按照边的权重从小到大排序
    sort(edges.begin(), edges.end(), cmp);
    // 从最小距离到最大距离添加边，直到图连通
    int last_dist = 0;
    int comp_num = n;
    DSU dsu(n);
    for (const Edge &e : edges) {
        if (dsu.unite(e.a, e.b)) {
            last_dist = e.dist;
            if (--comp_num == 1) { break; }
        }
    }
    cout << last_dist << endl;
}