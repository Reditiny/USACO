#include <iostream>
#include <fstream>
#include <vector>
#include <set>

using namespace std;

int main() {
    ifstream fin("revegetate.in");
    ofstream fout("revegetate.out");

    int n, m;
    fin >> n >> m;

    vector<set<int>> graph(n);
    vector<int> grasses(n, 0);
    // 记录牧场的边关系(一头牛喜欢的两个牧场之间有条边) 邻接表 有边相连的牧场不能种相同的种子
    for (int i = 0; i < m; i++) {
        int pasture1, pasture2;
        fin >> pasture1 >> pasture2;
        graph[pasture1 - 1].insert(pasture2 - 1);
        graph[pasture2 - 1].insert(pasture1 - 1);
    }
    // 从前往后依次确定牧场的种子
    for (int i = 0; i < n; i++) {
        set<int> exist_grasses;
        // 对于当前的 i 牧场，查看所有与 i 有边相连的 neighbor 牧场
        for (int neighbor : graph[i]) {
            // grasses[neighbor] 表示 neighbor 牧场种的种子
            // 0 表示 neighbor 牧场还没有种种子
            if (grasses[neighbor] != 0) {
                exist_grasses.insert(grasses[neighbor]);
            }
        }
        // 找到 i 牧场可以种的第一种种子，因为题目有要求按多解时按小序列输出
        for (int j = 1; j <= 4; j++) {
            if (exist_grasses.find(j) == exist_grasses.end()) {
                grasses[i] = j;
                break;
            }
        }
    }

    for (int grass : grasses) {
        fout << grass;
    }

    fin.close();
    fout.close();

    return 0;
}
