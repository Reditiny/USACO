#include <iostream>
#include <fstream>
#include <unordered_map>
#include <vector>

using namespace std;
vector<vector<bool>> fence(4003, vector<bool>(4003, false));  // 改为 2005 以通过时间限制
vector<vector<bool>> visited(4003, vector<bool>(4003, false));  // 改为 2005 以通过时间限制
int r = 2001, c = 2001;  // 改为 1002 以通过时间限制
int min_r = 2001, max_r = 2001, min_c = 2001, max_c = 2001;  // 改为 1002 以通过时间限制
unordered_map<char, vector<int>> DIRS = {
        {'N', {-1, 0}},
        {'S', {1, 0}},
        {'W', {0, -1}},
        {'E', {0, 1}}
};

// Flood Fill算法
void flood_fill(int r, int c) {
    if (visited[r][c] || fence[r][c]) {
        return;
    }
    visited[r][c] = true;
    for (const auto& dir : DIRS) {
        int x = r + dir.second[0];
        int y = c + dir.second[1];
        if (min_r <= x && x <= max_r && min_c <= y && y <= max_c) {
            flood_fill(x, y);
        }
    }
};

int main() {
    ifstream fin("gates.in");
    ofstream fout("gates.out");

    int N;
    fin >> N;
    string path;
    fin >> path;



    // 标记栅栏，按照2个单位的比例
    for (char ch : path) {
        fence[r + DIRS[ch][0]][c + DIRS[ch][1]] = true;
        fence[r + 2 * DIRS[ch][0]][c + 2 * DIRS[ch][1]] = true;
        r += 2 * DIRS[ch][0];
        c += 2 * DIRS[ch][1];
        min_r = min(min_r, r);
        max_r = max(max_r, r);
        min_c = min(min_c, c);
        max_c = max(max_c, c);
    }
    min_r -= 1;
    max_r += 1;
    min_c -= 1;
    max_c += 1;



    int result = 0;
    // 扩展1个单位以包含洪水填充中的边缘
    for (int i = min_r; i <= max_r; i++) {
        for (int j = min_c; j <= max_c; j++) {
            if (!visited[i][j] && !fence[i][j]) {
                flood_fill(i, j);
                result++;
            }
        }
    }

    fout << result - 1 << endl;
    fout.close();

    return 0;
}
