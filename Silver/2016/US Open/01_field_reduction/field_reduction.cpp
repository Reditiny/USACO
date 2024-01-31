#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <climits>
#include <set>
using namespace std;


/**
 * 找出除了三个点之外的最小和最大值
 */
long long findMinAndMaxWithAbsent(const vector<vector<int>>& a, int index1, int index2, int index3) {
    int minX = INT_MAX;
    int maxX = 0;
    int minY = INT_MAX;
    int maxY = 0;
    for (int i = 0; i < a.size(); i++) {
        if (i == index1 || i == index2 || i == index3) {
            continue;
        }
        minX = min(minX, a[i][0]);
        maxX = max(maxX, a[i][0]);
        minY = min(minY, a[i][1]);
        maxY = max(maxY, a[i][1]);
    }
    return static_cast<long long>(maxX - minX) * (maxY - minY);
}

int main() {
    freopen("reduce.in", "r", stdin);
    freopen("reduce.out", "w", stdout);
    int N;
    vector<vector<int>> cow_positions;
    set<vector<int>> candidates;
    cin >> N;

    for (int i = 0; i < N; i++) {
        vector<int> cow(2);
        cin >> cow[0] >> cow[1];
        cow_positions.push_back(cow);
    }
    // 四个方向找出至多 16 个候选点 使用 HashSet 去重
    sort(cow_positions.begin(), cow_positions.end(), [](const vector<int>& a, const vector<int>& b) {
        return a[0] < b[0];
    });
    for (int i = 0; i < 4; i++) {
        candidates.insert(cow_positions[i]);
        candidates.insert(cow_positions[cow_positions.size() - i - 1]);
    }
    sort(cow_positions.begin(), cow_positions.end(), [](const vector<int>& a, const vector<int>& b) {
        return a[1] < b[1];
    });
    for (int i = 0; i < 4; i++) {
        candidates.insert(cow_positions[i]);
        candidates.insert(cow_positions[cow_positions.size() - i - 1]);
    }
    // 将 HashSet 转换为 List 便于遍历
    vector<vector<int>> candidates_list(candidates.begin(), candidates.end());

    long long ans_area = LLONG_MAX;
    // 从候选点中去除三个点计算最小面积 虽然三重循环但是candidateList.size()最大为16 因此复杂度为 O(1)
    for (int i = 0; i < candidates_list.size(); i++) {
        for (int j = i + 1; j < candidates_list.size(); j++) {
            for (int k = j + 1; k < candidates_list.size(); k++) {
                long long area = findMinAndMaxWithAbsent(candidates_list, i, j, k);
                ans_area = min(ans_area, area);
            }
        }
    }

    cout << ans_area << endl;

    return 0;
}
