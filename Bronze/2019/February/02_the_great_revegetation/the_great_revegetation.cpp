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
    vector<int> grasses;
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
            // 如果 neighbor < grasses.size() 说明 neighbor 牧场已经种了种子
            // 那么就把这个种子加入到当前牧场的不能种的种子集合中

            // 例如 1，2，3 三个牧场，其中 1，2 有边相连，2，3 有边相连
            // 从前往后依次确定种子
            // 当前牧场是 1 时，此时 grasses = {} ，因为还没有开始种，所以可以种 1 号种子，种完 grasses = {1}
            // 然后时牧场 2，此时 grasses = {1}，遍历与 2 相连的牧场 1，3，发现 grasses[1] = 1, 说明 1 号牧场已经种了 1 号种子
            // 所以只能种 2 号种子，然后 grasses = {1, 2}
            // 然后是牧场 3，此时 grasses = {1, 2}，遍历与 3 相连的牧场 2，发现 grasses[2] = 2, 说明 2 号牧场已经种了 2 号种子
            // 所以只能种 1 号种子，然后 grasses = {1, 2, 1}
            if (neighbor < grasses.size()) {
                exist_grasses.insert(grasses[neighbor]);
            }
        }
        // 找到 i 牧场可以种的第一种种子，因为题目有要求按多解时按小序列输出
        for (int j = 1; j <= 4; j++) {
            if (exist_grasses.find(j) == exist_grasses.end()) {
                grasses.push_back(j);
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
