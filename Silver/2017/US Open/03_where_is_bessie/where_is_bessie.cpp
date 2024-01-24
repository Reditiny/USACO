#include <iostream>
#include <fstream>
#include <vector>
#include <array>
#include <queue>

using namespace std;

static int n;
static vector<vector<char>> image;
static vector<vector<bool>> visited;
// 用于限制当前遍历矩形的边界
static int i_min, i_max, j_min, j_max;
static array<array<int, 2>, 4> directions = {{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}};
/**
 * 广度优先搜索
 */
void bfs(int i, int j, char color) {
    if (i < i_min || j < j_min || i > i_max || j > j_max || visited[i][j] ||
        image[i][j] != color) {
        return;
    }

    visited[i][j] = true;
    for (const auto& d : directions) {
        bfs(i + d[0], j + d[1], color);
    }
}
/**
 * 判断矩形范围是否是PCL
 * 同样的颜色记做连通，广度优先搜索获得连通块的数量
 * 当且仅当有两种颜色，其中一种颜色只有一个连通块，另一种颜色有多个连通块
 */
bool is_pcl(int i1, int j1, int i2, int j2) {
    i_min = i1;
    i_max = i2;
    j_min = j1;
    j_max = j2;
    visited.assign(n, vector<bool>(n, false));
    // 广度优先搜索过程中记录每个颜色的连通块数量
    vector<int> region_count(26, 0);

    for (int i = i1; i <= i2; i++) {
        for (int j = j1; j <= j2; j++) {
            if (!visited[i][j]) {
                char curr_color = image[i][j];
                region_count[curr_color - 'A']++;
                bfs(i, j, curr_color);
            }
        }
    }

    // 根据连通块数量判断是否是PCL
    int color_count = 0;
    bool color_with_one_region = false;
    bool color_with_more_regions = false;

    for (int i = 0; i < region_count.size(); i++) {
        if (region_count[i] != 0) {
            color_count++;
        }
        if (region_count[i] == 1) {
            color_with_one_region = true;
        }
        if (region_count[i] > 1) {
            color_with_more_regions = true;
        }
    }

    return (color_count == 2 && color_with_one_region && color_with_more_regions);
}
/**
 * 判断矩形 pcl1 是否在 pcl2 内部
 */
bool is_inside(const vector<int>& pcl1, const vector<int>& pcl2) {
    return pcl1[0] >= pcl2[0] && pcl1[1] >= pcl2[1] &&
           pcl1[2] <= pcl2[2] && pcl1[3] <= pcl2[3];
}

int main() {
    ifstream fin("where.in");
    ofstream fout("where.out");

    fin >> n;
    image.resize(n, vector<char>(n));

    for (int i = 0; i < n; i++) {
        string row;
        fin >> row;
        for (int j = 0; j < n; j++) {
            image[i][j] = row[j];
        }
    }

    // 遍历每一个矩形区域，判断是否是PCL
    vector<vector<int>> pcls;
    for (int i1 = 0; i1 < n; i1++) {
        for (int j1 = 0; j1 < n; j1++) {
            for (int i2 = i1; i2 < n; i2++) {
                for (int j2 = j1; j2 < n; j2++) {
                    if (is_pcl(i1, j1, i2, j2)) {
                        pcls.push_back({i1, j1, i2, j2});
                    }
                }
            }
        }
    }

    // PCL内部的PCL不计算在内
    int pcl_count = 0;
    for (int i = 0; i < pcls.size(); i++) {
        bool valid_pcl = true;
        for (int j = 0; j < pcls.size(); j++) {
            if (i == j) {
                continue;
            }
            if (is_inside(pcls[i], pcls[j])) {
                valid_pcl = false;
                break;
            }
        }
        if (valid_pcl) {
            pcl_count++;
        }
    }

    fout << pcl_count << endl;

    fin.close();
    fout.close();

    return 0;
}
