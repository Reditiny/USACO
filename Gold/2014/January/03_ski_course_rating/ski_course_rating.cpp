#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

struct Edge {
    int u, v, w;
    bool operator<(const Edge& other) const { return w < other.w; }
};

// Disjoint Set Union 并查集
struct DSU {
    // e[i] < 0 表示 i 是根节点，且大小为 -e[i]
    // e[i] >= 0 表示 i 的父节点是 e[i]
    vector<int> e;
    // start_count[i] 表示以 i 为根节点的集合中有多少个起点
    vector<int> start_count;
    DSU(int N) : e(N, -1), start_count(N) {}
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
        start_count[x] += start_count[y];
        return true;
    }
};

int main() {
    freopen("skilevel.in", "r", stdin);
    freopen("skilevel.out", "w", stdout);
    int n, m, t;
    cin >> n >> m >> t;
    vector<vector<int>> grid(n, vector<int>(m));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) { cin >> grid[i][j]; }
    }
    vector<vector<int>> is_start(n, vector<int>(m));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) { cin >> is_start[i][j]; }
    }
    // 坐标转换为点的编号
    auto to_int = [&](int r, int c) -> int { return r * m + c; };
    vector<Edge> edges;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            // 每个点只需要考虑向右和向下的边
            if (i < n - 1) {
                edges.push_back({to_int(i, j), to_int(i + 1, j),abs(grid[i][j] - grid[i + 1][j])});
            }
            if (j < m - 1) {
                edges.push_back({to_int(i, j), to_int(i, j + 1),abs(grid[i][j] - grid[i][j + 1])});
            }
        }
    }
    // 边按照权重从小到大排序，重载 < 运算符
    sort(edges.begin(), edges.end());

    DSU dsu(n * m);

    // 初始化起点状态
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (is_start[i][j]) { dsu.start_count[to_int(i, j)] = 1; }
        }
    }

    long long ans = 0;
    for (Edge e : edges) {
        dsu.unite(e.u, e.v);
        int root = dsu.get(e.u);
        if (dsu.size(root) >= t) {
            // e.w 表示当前的 D，start_count[root] 表示当前集合中的起点数
            // 这些起点都可以通过 D 到达 T 个点
            ans += (long long)e.w * dsu.start_count[root];
            dsu.start_count[root] = 0;
        }
    }
    cout << ans << endl;
    return 0;
}