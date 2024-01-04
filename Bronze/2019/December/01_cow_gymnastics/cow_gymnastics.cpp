#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main() {
    ifstream fin("gymnastics.in");
    ofstream fout("gymnastics.out");

    int k, n;
    fin >> k >> n;

    vector<vector<int>> cows(k, vector<int>(n));

    for (int i = 0; i < k; i++) {
        for (int j = 0; j < n; j++) {
            fin >> cows[i][j];
        }
    }
    // 遍历所有组合中的每一对奶牛，判断 i 是否比 j 好
    vector<vector<bool>> better(n, vector<bool>(n, false));

    for (int l = 0; l < k; l++) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                better[cows[l][i] - 1][cows[l][j] - 1] = true;
            }
        }
    }

    int ans = 0;

    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            if (better[i][j] != better[j][i]) {
                ans++;
            }
        }
    }

    fout << ans << endl;

    fin.close();
    fout.close();

    return 0;
}
