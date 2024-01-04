#include <iostream>
#include <fstream>
#include <vector>
#include <unordered_set>
#include <unordered_map>
#include <set>
#include <string>
#include <sstream>

using namespace std;

int main() {
    ifstream fin("evolution.in");
    ofstream fout("evolution.out");

    int n;
    fin >> n;

    vector<unordered_set<string>> cows;
    unordered_set<string> features;

    for (int i = 0; i < n; i++) {
        int k;
        fin >> k;

        unordered_set<string> cow;
        for (int j = 0; j < k; j++) {
            string feature;
            fin >> feature;
            cow.insert(feature);
        }

        features.insert(cow.begin(), cow.end());
        cows.push_back(cow);
    }
    // HashSet为保证唯一 List方便遍历
    vector<string> feature_list(features.begin(), features.end());
    // 遍历所有两两(i,j)特征组合
    for (int i = 0; i < feature_list.size(); i++) {
        for (int j = i + 1; j < feature_list.size(); j++) {
            // 是否有牛同时具有(i,j)特征 是否有牛只具备i特征 是否有牛只具备j特征
            bool both = false, i_only = false, j_only = false;
            for (const auto& cow : cows) {
                bool i_feature = cow.count(feature_list[i]);
                bool j_feature = cow.count(feature_list[j]);

                if (i_feature && j_feature) {
                    both = true;
                } else if (i_feature) {
                    i_only = true;
                } else if (j_feature) {
                    j_only = true;
                }
            }
            // iOnly 说明 i 特征是某次进化的特征
            // jOnly 说明 j 特征是某次进化的特征
            // both 说明 i 特征和 j 特征是同一支牛先后进化出的特征
            // 正常的树不会出现三者同为真
            if (i_only && j_only && both) {
                fout << "no" << endl;
                return 0;
            }
        }
    }

    fout << "yes" << endl;

    fin.close();
    fout.close();

    return 0;
}
