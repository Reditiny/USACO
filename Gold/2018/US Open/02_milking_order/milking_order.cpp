#include <iostream>
#include <vector>
#include <queue>
using namespace std;

vector<int> ans;
vector<vector<int>> observations;
int n, m;
bool check(int x);

int main() {
    freopen("milkorder.in", "r", stdin);
    freopen("milkorder.out", "w", stdout);
    cin >> n >> m;
    observations.resize(m);
    for (int i = 0; i < m; i++) {
        int k;
        cin >> k;
        vector<int> v(k);
        for (int j = 0; j < k; j++) cin >> v[j];
        observations[i] = v;
    }
    // 二分查找 最大的 X
    int left = 0, right = m;
    while (left < right) {
        int mid = left + (right - left + 1) / 2;
        check(mid) ? left = mid : right = mid - 1;
    }
    for (int i = 0; i < n; i++) { cout << ans[i] << (i != n - 1 ? " " : ""); }
}

// 判断前 x 个 observation 是否满足无环
bool check(int x) {
    // 记录每个节点的入度
    vector<int> in(n+1);
    // 邻接表记录图
    vector<vector<int>> graph(n + 1);
    vector<int> res;
    // 将前 x 个 observation 记入图中
    for (int i = 0; i < x; i++) {
        for (int j = 0; j < observations[i].size() - 1; j++) {
            graph[observations[i][j]].push_back(observations[i][j + 1]);
            in[observations[i][j + 1]]++;
        }
    }
    // 拓扑排序 从入度为 0 的节点开始
    // 当遍历到一个节点后，将其所有邻接节点的入度减一，如果入度减到 0 则加入队列
    // 使用 priority_queue 保证字典序最小
    priority_queue<int, vector<int>, greater<int> > pq;
    for (int i = 1; i < n+1; i++)
        if (!in[i]) pq.push(i);
    while (pq.size()) {
        int x = pq.top();
        pq.pop();
        res.push_back(x);
        for (const int &i : graph[x])
            if (!(--in[i])) pq.push(i);
    }
    // 如果拓扑排序的结果不是 n 个节点，说明有环，返回 false
    bool success = res.size() == n;
    if (success) ans = res;
    return success;
}