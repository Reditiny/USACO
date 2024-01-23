#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ifstream fin("balancing.in");
    ofstream fout("balancing.out");

    int N;
    fin >> N;

    // 存储奶牛的坐标信息
    vector<vector<int>> cows(N, vector<int>(2));

    // 读入奶牛坐标信息
    for (int i = 0; i < N; i++) {
        fin >> cows[i][0] >> cows[i][1];
    }

    // 根据 x 坐标排序
    vector<vector<int>> cows_by_x = cows;
    sort(cows_by_x.begin(), cows_by_x.end());

    // 根据 y 坐标排序
    vector<vector<int>> cows_by_y = cows;
    sort(cows_by_y.begin(), cows_by_y.end(), [](const vector<int>& a, const vector<int>& b) {
        return a[1] < b[1];
    });

    // 分割函数，用于计算在垂直分割线 v_fence 处，左右两边的奶牛数量
    auto min_partition = [&N](int v_fence, const vector<vector<int>>& cows_by_y) {
        vector<vector<int>> left;
        vector<vector<int>> right;

        // 根据垂直分割线将奶牛分为左右两侧
        for (const auto& c : cows_by_y) {
            if (c[0] < v_fence) {
                left.push_back(c);
            } else {
                right.push_back(c);
            }
        }

        int most_balanced = N;
        int left_index = 0;
        int right_index = 0;

        // 遍历每个水平分割线，计算最不平衡的一侧奶牛数量
        while (left_index + right_index < N) {
            int h_fence = cows_by_y[left_index + right_index][1] + 1;

            // 统计左侧奶牛数量
            while (left_index < left.size() && h_fence > left[left_index][1]) {
                left_index += 1;
            }

            // 统计右侧奶牛数量
            while (right_index < right.size() && h_fence > right[right_index][1]) {
                right_index += 1;
            }

            int below_max = max(left_index, right_index);
            int above_max = max(static_cast<int>(left.size()) - left_index, static_cast<int>(right.size()) - right_index);
            most_balanced = min(most_balanced, max(above_max, below_max));
        }

        return most_balanced;
    };

    int most_balanced = N;
    int index_x = 0;

    // 遍历每个垂直分割线，计算最不平衡的一侧奶牛数量
    while (index_x < N) {
        int v_fence = cows_by_x[index_x][0] + 1;
        most_balanced = min(most_balanced, min_partition(v_fence, cows_by_y));

        // 移动到下一个垂直分割线
        while (index_x < N && cows_by_x[index_x][0] < v_fence) {
            index_x += 1;
        }
    }

    // 输出结果
    fout << most_balanced << endl;
    fout.close();

    return 0;
}
