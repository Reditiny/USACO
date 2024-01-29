#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ifstream fin("socdist2.in");
    ofstream fout("socdist2.out");

    int N;
    fin >> N;

    vector<vector<int>> cows;
    for (int i = 0; i < N; ++i) {
        int position, health;
        fin >> position >> health;
        cows.push_back({position, health});
    }
    sort(cows.begin(), cows.end());

    // 查找最小半径
    int radius = 1e6;
    for (int i = 1; i < N; ++i) {
        if (cows[i][1] != cows[i - 1][1]) {
            radius = min(radius, cows[i][0] - cows[i - 1][0]);
        }
    }

    int count = 0;
    int last = 0;
    while (last < N) {
        if (cows[last][1] == 1) {
            count += 1;
            // 从下一个位置开始预见，当下一个和前一个的距离大于等于半径时停止
            int cur = last + 1;
            while (cur < N) {
                if (cows[cur][0] == 0 || cows[cur][0] - cows[cur - 1][0] >= radius) {
                    // 超出了 cur - 1 的范围
                    last = cur;
                    break;
                }
                cur += 1;
            }
            // 所有点都在半径范围内，应该结束循环，否则 last 不会改变
            if (cur == N) {
                break;
            }
        } else {
            last += 1;
        }
    }

    fout << count << endl;
    return 0;
}
