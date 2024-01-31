#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
// 边的数据结构
struct Edge {
    int b;
    int i;
    int j;
};
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
    freopen("fencedin.in", "r", stdin);
    freopen("fencedin.out", "w", stdout);

    int A, B, n, m;
    cin >> A >> B >> n >> m;
    vector<int> a(n + 1), b(m + 1);
    for (int i = 1; i <= n; i++) { cin >> a[i]; }
    for (int i = 1; i <= m; i++) { cin >> b[i]; }
    a.push_back(A);
    b.push_back(B);
    // 所有栅栏从小到大排序
    sort(a.begin(), a.end());
    sort(b.begin(), b.end());
    // 最外层的栅栏
    n += 2;
    m += 2;
    // 将所有的边加入到 edges 中
    vector<Edge> edges;
    // 竖直的边
    int cur_sect = 0, row = 1;
    while (row < m) {
        for (int i = 0; i < n - 2; i++) {
            edges.push_back(Edge{b[row] - b[row - 1], cur_sect, cur_sect + 1});
            cur_sect++;
        }
        cur_sect++;
        row++;
    }
    // 水平的边
    cur_sect = n - 1;
    int col = 1;
    while (col < n) {
        int init = cur_sect;
        for (int i = 0; i < m - 2; i++) {
            edges.push_back(
                    Edge{a[col] - a[col - 1], cur_sect - (n - 1), cur_sect});
            cur_sect += (n - 1);
        }
        cur_sect = init + 1;
        col++;
    }
    // kruskal's 算法
    DSU dsu((n + 2) * (m + 2));
    // 按照边的权重从小到大排序，此处使用了 lambda 表达式，也可以在 Edge 结构体中重载 < 运算符
    sort(edges.begin(), edges.end(),[](const Edge &t, const Edge &y) { return t.b < y.b; });

    long long ans = 0;
    for (Edge &i : edges) {
        if (dsu.unite(i.i, i.j)) { ans += i.b; }
    }
    cout << ans << endl;
}
