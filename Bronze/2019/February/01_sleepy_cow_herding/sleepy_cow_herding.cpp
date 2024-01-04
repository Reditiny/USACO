#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ifstream fin("herding.in");
    ofstream fout("herding.out");

    const int cow_num = 3;
    vector<int> cows(cow_num);
    for (int i = 0; i < cow_num; i++) {
        fin >> cows[i];
    }
    sort(cows.begin(), cows.end());
    int gap1 = cows[1] - cows[0];
    int gap2 = cows[2] - cows[1];
    // 得到相邻两头牛之间的位置
    int min_gap = min(gap1, gap2) - 1;
    int max_gap = max(gap1, gap2) - 1;
    // 当 maxGap 较大时 总可以最少两步就放在一起 B___E______M -> E_B____M -> EMB
    // 最多需要 maxGap 步 较短一端的两头牛依次往最近的地方走  B___E______M -> EB_____M -> BE____M -> EB___M -> BE__M -> EB_M -> BEM
    // minGap == 1 时要额外考虑 B_E_____M -> BME 一步
    // minGap == 0 时要额外考虑 BE______M 只能从 maxGap 步中选取
    int min_move = min(2, min_gap);
    int max_move = max_gap;
    if (min_gap == 0) {
        min_move = min(2, max_gap);
    }
    fout << min_move << endl;
    fout << max_move << endl;

    fin.close();
    fout.close();

    return 0;
}
