#include <algorithm>
#include <iostream>
#include <vector>
#include <set>
#include <numeric>
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
    int n, m;
    cin >> n >> m;
    vector<vector<int>> edges(n);
    vector<int> degree(n);
    for(int i = 0; i < m; i++) {
        int a, b;
        cin >> a >> b;
        a--; b--;
        degree[a]++;
        degree[b]++;
        edges[a].push_back(b);
        edges[b].push_back(a);
    }
    int ret = 0;
    vector<bool> deleted(n);
    vector<int> active(n);
    for(int i = 0; i < n; i++) {
        active[i] = i;
    }
    // 遍历最少的朋友数，即最小的度 min_degree，然后找到最大连通图的大小 size，使 min_degree * size 最大
    // 然后删除图中度小于等于 min_degree 的点进行下一轮遍历
    for(int min_degree = 1; min_degree * min_degree <= m; min_degree++) {
        // 当前最小度下的解
        DSU dsu(n);
        for(int i: active) {
            for(auto j: edges[i]) {
                if(!deleted[j] && dsu.unite(i, j)) ret = max(ret, dsu.size(i) * min_degree);
            }
        }
        // 删除度小于等于 min_degree 的点
        vector<int> next_active;
        vector<int> to_delete;
        for(int i: active) {
            if(degree[i] == min_degree) {
                to_delete.push_back(i);
            }
        }
        while(to_delete.size()) {
            int i = to_delete.back(); to_delete.pop_back();
            if(deleted[i]) continue;
            deleted[i] = true;
            for(int j: edges[i]) {
                if(--degree[j] <= min_degree) {
                    to_delete.push_back(j);
                }
            }
            edges[i].clear();
        }
        for(int i: active) if(degree[i] > min_degree) next_active.push_back(i);
        active.swap(next_active);
    }
    cout << ret << endl;
}