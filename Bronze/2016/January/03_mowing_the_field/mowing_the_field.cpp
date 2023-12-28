#include <iostream>
#include <fstream>
#include <unordered_map>

using namespace std;

int main() {
    freopen("mowing.in", "r", stdin);
    freopen("mowing.out", "w", stdout);

    int n;
    cin >> n;
    // 给定草地 起点为中心 且保证足够大给定输入不会越界
    const int grid_size = 2001;
    int grass[grid_size][grid_size] = {0};
    int x = 1000;
    int y = 1000;
    // 从 1 开始自增地标记每次走过的格子
    int mark = 1;
    int ans = 10000;
    bool cell_more_than_once = false;

    unordered_map<string, pair<int, int>> move_map = {
            {"N", {0, 1}},
            {"S", {0, -1}},
            {"W", {-1, 0}},
            {"E", {1, 0}}
    };

    grass[x][y] = 1;

    for (int i = 0; i < n; i++) {
        string direction;
        int step;
        cin >> direction >> step;

        auto move = move_map[direction];
        for (int j = 0; j < step; j++) {
            y += move.second;
            x += move.first;
            mark++;
            // 值不为零说明这个格子被走过了 两次标记的差即为间隔 x
            if (grass[x][y] != 0) {
                cell_more_than_once = true;
                ans = min(ans, mark - grass[x][y]);
            }
            grass[x][y] = mark;
        }
    }
    if (cell_more_than_once) {
        cout << ans << endl;
    } else {
        cout << -1 << endl;
    }
    return 0;
}
