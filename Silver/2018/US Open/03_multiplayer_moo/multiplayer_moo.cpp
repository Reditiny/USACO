#include <fstream>
#include <iostream>
#include <map>
#include <unordered_set>
#include <vector>
using namespace std;

vector<vector<int>> grid;
vector<vector<pair<int, int>>> region_cells;
vector<vector<int>> directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//int region_id(int r) {
//    return grid[region_cells[r][0].first][region_cells[r][0].second];
//};

int main() {
    ifstream fin("multimoo.in");
    ofstream fout("multimoo.out");

    int N;
    fin >> N;
    vector<vector<int>> grid(N, vector<int>(N));
    for (int r = 0; r < N; r++) {
        for (int c = 0; c < N; c++) { fin >> grid[r][c]; }
    }

    // 记录每个单元格的区域 id
    vector<vector<int>> regions(N, vector<int>(N));
    // region_cells[i] 代表区域 i 中的所有单元格
    vector<vector<pair<int, int>>> region_cells;

    int one_biggest = 0;
    vector<vector<bool>> visited(N, vector<bool>(N));
    // 遍历单元格判断连接性
    for (int r = 0; r < N; r++) {
        for (int c = 0; c < N; c++) {
            if (visited[r][c]) { continue; }

            int curr_region = region_cells.size();

            vector<pair<int, int>> contained;

            vector<pair<int, int>> frontier{{r, c}};
            visited[r][c] = true;
            // 查找所有连接到当前单元格的单元格
            while (!frontier.empty()) {
                pair<int, int> curr = frontier.back();
                frontier.pop_back();

                contained.push_back(curr);
                regions[curr.first][curr.second] = curr_region;

                for (const auto& d : directions) {
                    int nr = curr.first + d[0];
                    int nc = curr.second + d[1];
                    if (0 <= nr && 0 <= nc && nr < N && nc < N &&
                        !visited[nr][nc] && grid[nr][nc] == grid[r][c]) {
                        visited[nr][nc] = true;
                        frontier.push_back({nr, nc});
                    }
                }
            }
            one_biggest = max(one_biggest, (int)contained.size());
            region_cells.push_back(contained);
        }
    }

    // 获取与其他区域相邻的区域
    vector<unordered_set<int>> neighbor_regions(region_cells.size());
    for (const vector<pair<int, int>> &reg : region_cells) {
        for (const auto &[r, c] : reg) {
            for (const auto& d : directions) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (0 <= nr && 0 <= nc && nr < N && nc < N &&
                    regions[nr][nc] != regions[r][c]) {
                    neighbor_regions[regions[r][c]].insert(regions[nr][nc]);
                }
            }
        }
    }
    // 记录已经处理过的区域对
    // 不使用 unordered_map 是因为 pair<int, int> 作为 key 需要额外指定 hash
    map<pair<int, int>, unordered_set<int>> visited_region_pair;
    int two_biggest = one_biggest;
    for (int r1 = 0; r1 < region_cells.size(); r1++) {
        for (int r2 : neighbor_regions[r1]) {
            pair<int, int> valid;
            valid.first = grid[region_cells[r1][0].first][region_cells[r1][0].second];
            valid.second = grid[region_cells[r2][0].first][region_cells[r2][0].second];
            if (valid.first > valid.second) {
                swap(valid.first, valid.second);
            }

            if (visited_region_pair[valid].count(r1)) { continue; }
            // 遍历整个区域
            int two_size = 0;
            vector<int> frontier{r1};
            vector<bool> curr_vis(region_cells.size());
            curr_vis[r1] = true;
            while (!frontier.empty()) {
                int curr = frontier.back();
                frontier.pop_back();
                two_size += region_cells[curr].size();
                visited_region_pair[valid].insert(curr);
                for (int nr : neighbor_regions[curr]) {
                    int nid = grid[region_cells[nr][0].first][region_cells[nr][0].second];;
                    if (!curr_vis[nr] &&
                        (valid.first == nid || valid.second == nid)) {
                        curr_vis[nr] = true;
                        frontier.push_back(nr);
                    }
                }
            }
            two_biggest = max(two_biggest, two_size);
        }
    }
    fout << one_biggest << '\n' << two_biggest << endl;

    fin.close();
    fout.close();

    return 0;
}