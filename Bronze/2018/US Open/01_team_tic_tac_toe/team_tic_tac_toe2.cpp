#include <iostream>
#include <fstream>
#include <vector>
#include <set>
#include <algorithm>

using namespace std;

vector<string> board(3);
// winners[0] 代表单牛获胜排列，winners[1] 代表双牛获胜排列
// 不使用 unordered_set 是因为 unordered_set 需要自定义 hash 函数
// 使用 set 中可以直接比较两个 set 是否相等，而不需要自定义比较函数
set<set<char>> winners[2];
// 获胜排列
void insert(vector<pair<int, int>> coordinates) {
    set<char> contained;
    for (const pair<int, int> &p : coordinates) {
        contained.insert(board[p.first][p.second]);
    }
    if (contained.size() >= 1 &&  contained.size() <=2) {
        winners[contained.size() - 1].insert(contained);
    }
}

int main() {
    ifstream fin("tttt.in");
    ofstream fout("tttt.out");
    for (int r = 0; r < 3; r++) { fin >> board[r]; }
    // 行
    for (int i = 0; i < 3; i++) { insert({{i, 0}, {i, 1}, {i, 2}}); }
    // 列
    for (int i = 0; i < 3; i++) { insert({{0, i}, {1, i}, {2, i}}); }
    // 对角线
    insert({{0, 0}, {1, 1}, {2, 2}});
    insert({{2, 0}, {1, 1}, {0, 2}});

    fout << winners[0].size() << endl;
    fout << winners[1].size() << endl;
}
