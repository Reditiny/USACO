#include <iostream>
#include <fstream>
#include <vector>
#include <unordered_set>
#include <queue>

using namespace std;

vector<vector<int> > graph;

int main() {
    ifstream fin("factory.in");
    ofstream fout("factory.out");

    int n;
    fin >> n;

    graph.resize(n);
  
    
    for (int i = 1; i < n; i++) {
        int station1, station2;
        fin >> station1 >> station2;
        graph[station2 - 1].push_back(station1 - 1);
    }
    int root = -1;
    for (int i = 0; i < n; i++) {
        vector<bool> visited(n);
        visited[i] = true;
        // DFS 遍历查看是否能够到达所有的节点
        vector<int> reached{i};
        while (!reached.empty()) {
            int cur = reached.back();
            reached.pop_back();
            for (int n: graph[cur]) {
                if (!visited[n]) {
                    visited[n] = true;
                    reached.push_back(n);
                }
            }
        }
        // 判断是否能够到达所有的节点
        bool valid = true;
        for (int check_s = 0; check_s < n; check_s++) {
            if (!visited[check_s]) {
                valid = false;
                break;
            }
        }

        if (valid) {
            root = i + 1;
            break;
        }

    }

    fout << root << endl;

    fin.close();
    fout.close();

    return 0;
}
