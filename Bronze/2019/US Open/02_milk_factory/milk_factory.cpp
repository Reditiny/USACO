#include <iostream>
#include <fstream>
#include <vector>
#include <unordered_set>
#include <queue>

using namespace std;

vector<vector<int>> graph;
unordered_set<int> target_stations;
int ans;
// 从 start 开始进行广度优先搜索
// 返回值为从 start 开始可以到达的所有工厂
unordered_set<int> bfs(int start) {
    unordered_set<int> visited;
    visited.insert(start);
    queue<int> q;
    q.push(start);

    while (!q.empty()) {
        int cur = q.front();
        q.pop();

        for (int neighbor : graph[cur]) {
            if (visited.find(neighbor) == visited.end()) {
                visited.insert(neighbor);
                q.push(neighbor);
            }
        }
    }

    return visited;
}

int main() {
    ifstream fin("factory.in");
    ofstream fout("factory.out");

    int n;
    fin >> n;
    ans = n;

    graph.resize(n);
    for (int i = 0; i < n; i++) {
        target_stations.insert(i);
    }

    for (int i = 1; i < n; i++) {
        int station1, station2;
        fin >> station1 >> station2;
        graph[station1 - 1].push_back(station2 - 1);
    }
    // 对于每个工厂，都进行一次广度优先搜索，得到从该工厂可以到达的所有工厂
    // 最终的交集即为目标工厂
    for (int i = 0; i < n; i++) {
        unordered_set<int> reached_station = bfs(i);
        for (int station : target_stations) {
            if (reached_station.find(station) == reached_station.end()) {
                target_stations.erase(station);
            }
        }

        if (target_stations.empty()) {
            break;
        }
    }

    if (target_stations.empty()) {
        fout << "-1" << endl;
    } else {
        for (int station : target_stations) {
            ans = min(ans, station);
        }
        fout << ans + 1 << endl;
    }

    fin.close();
    fout.close();

    return 0;
}
