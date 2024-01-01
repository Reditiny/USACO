#include <iostream>
#include <fstream>
#include <vector>
#include <unordered_set>
#include <map>
#include <string>

using namespace std;

int main() {
    freopen("cownomics.in", "r", stdin);
    ofstream fout("cownomics.out");

    int n, m;
    cin >> n >> m;

    vector<string> spotty(n);
    vector<string> plain(n);
    for (int i = 0; i < n; i++) {
        cin >> spotty[i];
    }
    for (int i = 0; i < n; i++) {
        cin >> plain[i];
    }
    map<char, int> char_to_int;
    char_to_int['A'] = 0;
    char_to_int['C'] = 1;
    char_to_int['G'] = 2;
    char_to_int['T'] = 3;
    int ans = 0;
    // 枚举每一列两种牛的基因
    for (int i = 0; i < m; i++) {
        bool spotty_bool[] = {false, false, false, false};
        bool plain_bool[] = {false, false, false, false};
        for (int j = 0; j < n; j++) {
            spotty_bool[char_to_int[spotty[j][i]]] = true;
            plain_bool[char_to_int[plain[j][i]]] = true;
        }
        bool flag = true;
        for (int j = 0; j < 4; j++) {
            if (spotty_bool[j] && plain_bool[j]) {
                flag = false;
                break;
            }
        }
        if (flag) {
            ans++;
        }
    }

    fout << ans << endl;

    fclose(stdin);
    fout.close();

    return 0;
}
