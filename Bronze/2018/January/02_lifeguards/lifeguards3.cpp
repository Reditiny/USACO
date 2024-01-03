#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

vector<vector<int>> live_guards;
int times[1001];
int covered = 0;
// 计算开除指定救生员后可以覆盖的时间
int cover_time_miss_one(int missed) {
    int times_copy[1001];
    copy(begin(times), end(times), begin(times_copy));
    int covered_copy = covered;
    for (int i = live_guards[missed][0]; i < live_guards[missed][1]; i++) {
        if (times_copy[i] == 1) {
            covered_copy--;
        }
        times_copy[i]--;
    }
    return covered_copy;
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
        // 依次计算开除救生员 i 之后可以覆盖的时间
        for (int t = live_guards[i][0]; t < live_guards[i][1]; t++) {
            if (times[t] == 0) {
                covered++;
            }
            times[t]++;
        }
    }

    int ans = 0;

    for (int i = 0; i < live_guards.size(); i++) {
        ans = max(ans, cover_time_miss_one(i));
        if (ans == covered) {
            break;
        }
    }

    fout << ans << endl;

    fin.close();
    fout.close();

    return 0;
}
