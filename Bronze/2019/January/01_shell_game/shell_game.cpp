#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

int shellCount = 3;
int n;
vector<int> guesses;
vector<vector<int>> swaps;

void swap(vector<bool>& shells, int i, int j) {
    bool temp = shells[i];
    shells[i] = shells[j];
    shells[j] = temp;
}
// 计算初始位置为 initialLocation 时的分数
int score(int initialLocation) {
    vector<bool> shells(shellCount, false);
    int score = 0;
    shells[initialLocation] = true;
    for (int i = 0; i < n; i++) {
        swap(shells, swaps[i][0], swaps[i][1]);
        if (shells[guesses[i]]) {
            score++;
        }
    }
    return score;
}

int main() {
    ifstream fin("shell.in");
    ofstream fout("shell.out");

    fin >> n;

    swaps.resize(n, vector<int>(2));
    guesses.resize(n);

    for (int i = 0; i < n; i++) {
        fin >> swaps[i][0] >> swaps[i][1] >> guesses[i];
        swaps[i][0]--;
        swaps[i][1]--;
        guesses[i]--;
    }

    int ans = 0;
    for (int i = 0; i < shellCount; i++) {
        ans = max(ans, score(i));
    }

    fout << ans << endl;

    fin.close();
    fout.close();

    return 0;
}
