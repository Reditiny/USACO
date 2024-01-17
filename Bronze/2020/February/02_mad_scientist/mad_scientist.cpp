#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main() {
    ifstream fin("breedflip.in");
    ofstream fout("breedflip.out");

    int n;
    fin >> n;

    vector<char> a(n), b(n);
    for (int i = 0; i < n; i++) {
        fin >> a[i];
    }

    for (int i = 0; i < n; i++) {
        fin >> b[i];
    }
    // 每次改变连续的不同的牛 寻找到每一段的起点终点即可
    //  |--| |---|
    // GGGGGHHGGGGGH
    // GHHHHHGHHHHGH
    int ans = 0;
    // start == false 说明正在寻找起点
    // start == true  说明找到起点，正在寻找终点
    bool start = false;
    for (int i = 0; i < n; i++) {
        if (start && a[i] == b[i]) {
            // 找到当前的终点，准备寻找下一个起点
            start = false;
        } else if (!start && a[i] != b[i]) {
            // 找到起点，准备寻找终点
            ans++;
            start = true;
        }
    }

    fout << ans << endl;

    fin.close();
    fout.close();

    return 0;
}
