#include <iostream>
#include <fstream>
#include <unordered_set>

using namespace std;

int main() {
    ifstream fin("whereami.in");
    ofstream fout("whereami.out");

    int n;
    fin >> n;

    string boxes;
    fin >> boxes;

    unordered_set<string> mailBox;
    int ans = n;
    bool ok;
    // 依次截取不同长度的子串，判断是否有重复
    for (int scope = 1; scope <= n; scope++) {
        ok = true;
        for (int i = 0; i <= n - scope; i++) {
            string sub = boxes.substr(i, scope);
            if (mailBox.find(sub) == mailBox.end()) {
                mailBox.insert(sub);
            } else {
                ok = false;
                break;
            }
        }
        if (ok) {
            ans = scope;
            break;
        }
    }

    fout << ans << endl;

    fin.close();
    fout.close();

    return 0;
}
