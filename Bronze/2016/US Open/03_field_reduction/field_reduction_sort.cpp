#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ifstream fin("reduce.in");
    ofstream fout("reduce.out");

    int N;
    fin >> N;

    vector<pair<int, int>> cows_by_x;

    for (int i = 0; i < N; ++i) {
        int x, y;
        fin >> x >> y;
        cows_by_x.push_back({x, y});
    }

    sort(cows_by_x.begin(), cows_by_x.end());
    vector<pair<int, int>> cows_by_y = cows_by_x;
    sort(cows_by_y.begin(), cows_by_y.end(), [](const pair<int, int>& cow1, const pair<int, int>& cow2) {
        return cow1.second < cow2.second;
    });

    vector<pair<int, int>> points_to_drop = {cows_by_x[0], cows_by_x.back(), cows_by_y[0], cows_by_y.back()};
    int answer = 40000 * 40000;

    for (const auto& point : points_to_drop) {
        int min_x = 40000;
        int max_x = 0;
        int min_y = 40000;
        int max_y = 0;

        for (const auto& cow : cows_by_x) {
            if (cow == point) {
                continue;
            }
            min_x = min(min_x, cow.first);
            max_x = max(max_x, cow.first);
            min_y = min(min_y, cow.second);
            max_y = max(max_y, cow.second);
        }

        answer = min(answer, (max_x - min_x) * (max_y - min_y));
    }

    fout << answer << endl;
    fout.close();

    return 0;
}
