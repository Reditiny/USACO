#include <iostream>
#include <fstream>
#include <vector>
#include <unordered_set>
#include <algorithm>

using namespace std;

int main() {
    ifstream fin("guess.in");
    ofstream fout("guess.out");

    int n;
    fin >> n;

    // 动物编号 -> 特征集合
    vector<unordered_set<string>> animals(n);
    for (int i = 0; i < n; i++) {
        string animal;
        int k;
        fin >> animal >> k;
        animals[i] = unordered_set<string>();
        for (int j = 0; j < k; j++) {
            string feature;
            fin >> feature;
            animals[i].insert(feature);
        }
    }

    int ans = 0;
    // 枚举所有可能的两两组合求交集大小 因为其他动物全都会在这个交集中的问题时被剔除
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            unordered_set<string> intersection;
            for (const string& feature : animals[i]) {
                if (animals[j].count(feature)) {
                    intersection.insert(feature);
                }
            }
            ans = max(ans, static_cast<int>(intersection.size()) + 1);
        }
    }

    fout << ans << endl;

    fin.close();
    fout.close();

    return 0;
}
