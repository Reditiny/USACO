#include <iostream>
#include <climits>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;




int main() {
    freopen("reduce.in", "r", stdin);
    freopen("reduce.out", "w", stdout);
    int N;
    vector<vector<int>> cow_positions;

    vector<vector<int>> cows_sort_by_x;
    vector<vector<int>> cows_sort_by_y;
    cin >> N;

    for (int i = 0; i < N; i++) {
        vector<int> cow(2);
        cin >> cow[0] >> cow[1];
        cow_positions.push_back(cow);
    }
    sort(cow_positions.begin(), cow_positions.end(), [](const vector<int>& a, const vector<int>& b) {
        return a[0] < b[0];
    });
    cows_sort_by_x = cow_positions;
    sort(cow_positions.begin(), cow_positions.end(), [](const vector<int>& a, const vector<int>& b) {
        return a[1] < b[1];
    });
    cows_sort_by_y = cow_positions;

    int ans_area = 40000 * 40000;
    for (int numberOfMinx = 0; numberOfMinx < 4; numberOfMinx++) {
        for (int numberOfMaxx = 0; numberOfMaxx < 4; numberOfMaxx++) {
            for (int numberOfMiny = 0; numberOfMiny < 4; numberOfMiny++) {
                for (int numberOfMaxy = 0; numberOfMaxy < 4; numberOfMaxy++) {
                    if (numberOfMinx + numberOfMaxx + numberOfMiny + numberOfMaxy != 3) {
                        continue;
                    }
                    // 因为按 x 排序和按 y 排序移除牛的时候可能会有牛重复被移除，所以用 set 去重
                    set<vector<int>> points_removed;
                    // 按 x 排序移除若干头牛直接移除前 numberOfMinx 和后 numberOfMaxx
                    for (int i = 0; i < numberOfMinx; i++) {
                        points_removed.insert(cows_sort_by_x[i]);
                    }
                    // 按 y 排序确定要移除的剩下的若干头牛
                    // 如果牛已经在 x 排序中被移除了则跳过
                    for (int i = 0; i < numberOfMaxx; i++) {
                        points_removed.insert(cows_sort_by_x[N - 1 - i]);
                    }

                    int yStart = 0, count = 0;
                    while (count < numberOfMiny) {
                        if (!points_removed.count(cows_sort_by_y[yStart])) {
                            points_removed.insert(cows_sort_by_y[yStart]);
                            count++;
                        }
                        yStart++;
                    }

                    int yEnd = N - 1;
                    count = 0;
                    while (count < numberOfMaxy) {
                        if (!points_removed.count(cows_sort_by_y[yEnd])) {
                            points_removed.insert(cows_sort_by_y[yEnd]);
                            count++;
                        }
                        yEnd--;
                    }
                    // 当 x 和 y 的移除都完成后 numberOfMinx 位置上的牛不一定还在因为它可能在 y 排序中被移除了
                    // 同理 yStart 位置上的牛也不一定还在因为它可能在 x 排序中被移除了
                    // 所以要从这些位置开始查看牛是否还在
                    int xMin = numberOfMinx, xMax = N - 1 - numberOfMaxx;
                    int yMin = yStart, yMax = yEnd;

                    while (points_removed.count(cows_sort_by_x[xMin])) {
                        xMin++;
                    }

                    while (points_removed.count(cows_sort_by_x[xMax])) {
                        xMax--;
                    }

                    while (points_removed.count(cows_sort_by_y[yMin])) {
                        yMin++;
                    }

                    while (points_removed.count(cows_sort_by_y[yMax])) {
                        yMax--;
                    }

                    int distanceY = cows_sort_by_y[yMax][1] - cows_sort_by_y[yMin][1];
                    int distanceX = cows_sort_by_x[xMax][0] - cows_sort_by_x[xMin][0];

                    int area = distanceX * distanceY;

                    if (area < ans_area) {
                        ans_area = area;
                    }
                }
            }
        }
    }

    cout << ans_area << endl;

    return 0;
}
