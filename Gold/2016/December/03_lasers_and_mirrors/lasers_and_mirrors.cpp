#include <cstdio>
#include <iostream>
#include <queue>
#include <unordered_map>
#include <vector>
using namespace std;

int main() {
    freopen("lasers.in", "r", stdin);
    freopen("lasers.out", "w", stdout);
    int n;
    cin >> n;
    // 存储所有点的坐标  0:laser 1:barn 2~n+1:fence
    vector<pair<int, int>> points(n + 2);
    // lines[0] key 为横坐标 value 为横坐标相同的点的下标
    // lines[1] key 为纵坐标 value 为纵坐标相同的点的下标
    unordered_map<int, vector<int>> lines[2];
    for (int i = 0; i < n + 2; i++) {
        cin >> points[i].first >> points[i].second;
        lines[0][points[i].first].push_back(i);
        lines[1][points[i].second].push_back(i);
    }
    // pair<int, bool> first 为点的下标 second true 为横坐标 false 为纵坐标
    // 初始从 laser 出发可以发出水平和竖直两个方向的光
    queue<pair<int, bool>> q;
    q.push({0, true});
    q.push({0, false});
    // dist[i] 为点 i 到 laser 的最短折射次数
    vector<int> dist(n + 2, 1e9);
    dist[0] = 0;
    // BFS 广度优先搜索计算最短折射次数
    while (!q.empty()) {
        int cur_fence_index = q.front().first;
        bool beam_direction = q.front().second;
        q.pop();
        // 对于当前的 fence 和 beam_direction 来确定下一个 fence
        //        fence4
        //                   fence3
        //        fence2
        //
        //  ----> fence1
        // 如上箭头为光线，fence1 为当前的 fence
        // 折射后应该让 fence2 与 fence4 入队，且光线方向为竖直
        int direction = (beam_direction ? 0 : 1);
        int coordinate = (beam_direction ? points[cur_fence_index].first : points[cur_fence_index].second);
        // 遍历所有和当前 fence 的 coordinate 在 direction 方向上同一条线的 fence
        for (int point : lines[direction][coordinate]) {
            if (dist[point] == 1e9) {
                q.push({point, !beam_direction});
                dist[point] = dist[cur_fence_index] + 1;
            }
        }
    }
    cout << (dist[1] == 1e9 ? -1 : dist[1] - 1) << endl;
    return 0;
}