#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

const long long MOD = 2019201997LL;
const long long FACTOR1 = 2019201913LL;
const long long FACTOR2 = 2019201949LL;

// 计算两头奶牛愿意走的距离
long long get_miles(long long a, long long b) {
    a++, b++;
    return (a * FACTOR1 + b * FACTOR2) % MOD;
}
// Prim's 算法构建最小生成树
// 算法流程：
// 1. 从任意一个节点开始，将这个节点加入到 MST 中
// 2. 从 MST 中的节点出发，找到一个到 MST 距离最近的节点，将这个节点加入到 MST 中
// 3. 重复步骤 2，直到 MST 中包含了所有的节点
vector<long long> prim(int N) {
    vector<long long> dist(N, MOD);
    vector<bool> visited(N, false);
    for (int i = 0; i < N; i++) {
        // 找到距离 MST 最近的节点
        int min_dist_node = -1;
        for (int j = 0; j < N; j++) {
            if (!visited[j] && (min_dist_node < 0 || dist[j] < dist[min_dist_node])) {
                min_dist_node = j;
            }
        }
        // 将这个节点加入到 MST 中
        visited[min_dist_node] = true;
        // 更新其他节点到 MST 的距离
        for (int j = 0; j < N; j++) {
            if (!visited[j]) {
                dist[j] = min(dist[j], get_miles(min(min_dist_node, j),max(min_dist_node, j)));
            }
        }
    }
    return dist;
}

int main() {
    freopen("walk.in", "r", stdin);
    freopen("walk.out", "w", stdout);

    int N, K;
    cin >> N >> K;
    // Prim's 算法构建最小生成树
    vector<long long> mst = prim(N);
    sort(mst.begin(), mst.end());
    // 最小生成树里的边每减少一个就可以分出一组
    // 将最大的 K - 1 个边去掉，就可以分出 K 组
    // 此时最大的边 (N-1) - (K-1) = N - K 就是答案
    cout << mst[N - K] << endl;
}