#include <iostream>
#include <vector>
#include <array>
#include <algorithm>
#include <cmath>
using namespace std;
const int MAX_Y = 10;
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
struct Point{
    long long x, y;
    int index;
    Point(long long x, long long y, int index) : x(x), y(y), index(index) {}
    bool operator<(const Point &p) const { return x < p.x; }
};
struct Edge{
    long long weight;
    int a, b;
    bool operator<(const Edge &e) const { return weight < e.weight; }
};
int main() {
    int n;
    cin >> n;
    // 存储所有牛的位置和编号，并按照 x 坐标从小到大排序
    vector<Point> points;
    for (int i = 0; i < n; i++) {
        long long x, y;
        cin >> x >> y;
        points.push_back(Point(x, y, i));
    }
    sort(points.begin(), points.end());
    // 存储每个 y 坐标上的 x 最大的点
    vector<Point> buffer(11, {-1, -1, -1});
    // 存储所有的边
    vector<Edge> edges;
    // 给定三个点 p1 p2 p3，坐标分别为(x1, y1) (x2, y2) (x3, y3)，其中x1 < x2 < x3
    // 如果 y1 = y2, p1p3 的长度将始终大于 p1p2 和 p2p3 的长度
    // 使用 Kruskal 算法时对于 p1 p2 p3 三个点 p1p3 这条边不会被选择到最小生成树中
    for (int i = 0; i < n; i++) {
        for (int j = 0; j <= MAX_Y; j++) {
            // 对于点 points[i] 来说，如果 buffer[j] 存在，只需要考虑 points[i] 和 buffer[j] 之间的距离
            if (buffer[j].index != -1) {
                long long dist = pow(points[i].x - buffer[j].x, 2) + pow(points[i].y - buffer[j].y, 2);
                edges.push_back({dist, points[i].index, buffer[j].index});
            }
        }
        buffer[points[i].y] = points[i];
    }
    // Kruskal 算法建立最小生成树
    sort(edges.begin(), edges.end());
    long long ans = 0;
    DSU dsu(n);
    for (int i = 0; i < edges.size(); i++) {
        if (dsu.unite(edges[i].a,edges[i].b)) { ans += edges[i].weight; }
    }
    cout << ans << endl;
    return 0;
}