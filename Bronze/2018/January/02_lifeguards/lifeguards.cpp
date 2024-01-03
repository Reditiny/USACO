#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ifstream fin("lifeguards.in");
    ofstream fout("lifeguards.out");

    int n;
    fin >> n;

    vector<pair<int, int>> intervals;
    for (int i = 0; i < n; i++) {
        int start, end;
        fin >> start >> end;
        intervals.push_back({start, end});
    }
    // 按照起始时间排序
    sort(intervals.begin(), intervals.end());
    // 计算总覆盖时间 每一个缺失后所减少的时间 需要单独处理首尾两个区间
    int gap = min(intervals[0].second, intervals[1].first) - intervals[0].first;
    int cover = intervals[0].second - intervals[0].first;
    int last_end = intervals[0].second;
    for (int i = 1; i < n - 1; i++) {
        // 对于当前区间 i 计算其缺失后所减少的时间  i 区间可能会影响后续多个区间
        // ---
        //      ------(i)
        //           ---(j)
        //                   ---
        // ---
        //  ---------------------(i)
        //          ---(j)
        //                      ---
        auto cur_interval = intervals[i];
        int gap_last_end = last_end;
        int cur_gap = 0;

        for (int j = i + 1; j < n; j++) {
            // 依次探查后续区间
            auto next_interval = intervals[j];
            // i 区间在 j 区间范围内所产生的 gap
            int cur_gap_end = min(cur_interval.second, next_interval.first);
            int cur_gap_start = max(gap_last_end, cur_interval.first);
            cur_gap += max(0, cur_gap_end - cur_gap_start);
            gap_last_end = max(gap_last_end, next_interval.second);
            // i 区间可能影响多个区间 直到 j 区间的开端大于 i 区间的结尾
            if (gap_last_end > cur_interval.second) {
                break;
            }
        }

        gap = min(gap, cur_gap);
        // 计算总覆盖时间
        if (cur_interval.second > last_end) {
            cover += cur_interval.second - max(cur_interval.first, last_end);
            last_end = cur_interval.second;
        }
    }

    if (intervals[n - 1].second > last_end) {
        cover += intervals[n - 1].second - max(intervals[n - 1].first, last_end);
        gap = min(gap, intervals[n - 1].second - last_end);
    } else {
        gap = 0;
    }

    fout << cover - gap << endl;

    fin.close();
    fout.close();

    return 0;
}
