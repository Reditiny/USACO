#include <iostream>
#include <fstream>
#include <climits>

using namespace std;

const int MAX_COLOR = 9;

int main() {
    ifstream fin("art.in");
    ofstream fout("art.out");

    int n;
    fin >> n;
    // 记录每个颜色的矩形范围 为省去后面赋值时 index - 1 的麻烦 index 从 1 开始
    int left[MAX_COLOR + 1];
    int right[MAX_COLOR + 1];
    int down[MAX_COLOR + 1];
    int up[MAX_COLOR + 1];
    bool valid_start[MAX_COLOR + 1] = {false};
    int grid[1000][1000];

    for (int c = 1; c <= MAX_COLOR; c++) {
        left[c] = up[c] = INT_MAX;
        right[c] = down[c] = -1;
    }

    for (int row = 0; row < n; row++) {
        string line;
        fin >> line;
        for (int col = 0; col < n; col++) {
            int cur_color = line[col] - '0';
            grid[row][col] = cur_color;
            if (cur_color != 0) {
                left[cur_color] = min(left[cur_color], col);
                right[cur_color] = max(right[cur_color], col);
                down[cur_color] = max(down[cur_color], row);
                up[cur_color] = min(up[cur_color], row);
                valid_start[cur_color] = true;
            }
        }
    }
    // 检查每个 color 的矩形范围
    for (int color = 1; color <= MAX_COLOR; color++) {
        for (int row = up[color]; row <= down[color]; row++) {
            for (int col = left[color]; col <= right[color]; col++) {
                // 若 color 边界内的格子不是 color 颜色则该格子的颜色一定不是起始颜色(覆盖了当前color)
                if (grid[row][col] != color) {
                    valid_start[grid[row][col]] = false;
                }
            }
        }
    }

    int valid_start_count = 0;
    for (bool ok : valid_start) {
        if (ok) {
            valid_start_count++;
        }
    }

    fout << valid_start_count;
    fin.close();
    fout.close();

    return 0;
}
