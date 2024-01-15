#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main() {
    ifstream fin("pails.in");
    ofstream fout("pails.out");

    int X, Y, M;
    fin >> X >> Y >> M;
    // dp[i] 表示是否可以得到 i
    // 动态转移方程为 dp[i] = dp[i - x] || dp[i - y]
    vector<bool> dp(M + 1, false);
    dp[0] = true;

    for (int i = 0; i <= M; i++) {
        if (!dp[i]) {
            continue;
        }

        if (i + X <= M) {
            dp[i + X] = true;
        }

        if (i + Y <= M) {
            dp[i + Y] = true;
        }
    }

    for (int i = M; i >= 0; i--) {
        if (dp[i]) {
            fout << i << endl;
            break;
        }
    }

    fout.close();

    return 0;
}
