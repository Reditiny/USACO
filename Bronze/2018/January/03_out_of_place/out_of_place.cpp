#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ifstream fin("outofplace.in");
    ofstream fout("outofplace.out");

    int n;
    fin >> n;

    vector<int> cows(n);
    for (int i = 0; i < n; i++) {
        fin >> cows[i];
    }
    // 最优的交换方式就是每次都把一个不在最终位置上的牛放到最终位置上(不交换其他已经在最终位置上的牛)
    vector<int> orderedCows = cows;
    sort(orderedCows.begin(), orderedCows.end());

    int ans = 0;
    for (int i = 0; i < n; i++) {
        if (orderedCows[i] != cows[i]) {
            ans++;
        }
    }
    // 有 ans 个牛不在自己最终的位置上
    // 当通过交换把 ans - 1 个牛放在最终的位置上时，最后那只牛也就已经在正确的位置了
    fout << ans - 1 << endl;

    fin.close();
    fout.close();

    return 0;
}
