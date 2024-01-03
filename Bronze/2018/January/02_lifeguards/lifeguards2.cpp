#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

vector<vector<int>> live_guards;
// 计算开除指定救生员后可以覆盖的时间
int cover_time_miss_one(int missed) {
    vector<int> life_guard_cover(1001, 0);
    for (int i = 0; i < live_guards.size(); i++) {
        if (i == missed) {
            continue;
        }
        for (int j = live_guards[i][0]; j < live_guards[i][1]; j++) {
            life_guard_cover[j]++;
        }
    }
    int cover_count = 0;
    for (int i = 0; i < life_guard_cover.size(); i++) {
        if (life_guard_cover[i] > 0) {
            cover_count++;
        }
    }
    return cover_count;
}

int main() {
    ifstream fin("lifeguards.in");
    ofstream fout("lifeguards.out");

    int n;
    fin >> n;

    live_guards.resize(n);
    for (int i = 0; i < n; i++) {
        live_guards[i].resize(2);
        fin >> live_guards[i][0] >> live_guards[i][1];
    }

    int ans = 0;
    // 依次计算开除救生员 i 之后可以覆盖的时间
    for (int i = 0; i < live_guards.size(); i++) {
        ans = max(ans, cover_time_miss_one(i));
    }

    fout << ans << endl;

    fin.close();
    fout.close();

    return 0;
}
