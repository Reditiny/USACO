#include <iostream>
#include <fstream>
#include <vector>
#include <unordered_set>

using namespace std;



int main() {
    ifstream fin("backforth.in");
    ofstream fout("backforth.out");
    vector<int> buckets1;
    vector<int> buckets2;
    unordered_set<int> milks;

    for (int i = 0; i < 10; i++) {
        int bucket;
        fin >> bucket;
        buckets1.push_back(bucket);
    }

    for (int i = 0; i < 10; i++) {
        int bucket;
        fin >> bucket;
        buckets2.push_back(bucket);
    }
    // 情况1 拿着相同的桶走了两个来回
    milks.insert(0);
    // 情况2 拿着相同的桶走了一个来回，然后又各拿一个桶
    for (int k1 : buckets1) {
        for (int k2 : buckets2) {
            milks.insert(k2 - k1);
        }
    }
    // 情况3 周二周四拿了自己的不同的桶  周三周五拿了自己的不同的桶
    // 假设周三时拿了周二拿过去的桶，这时满足情况2，所以在此不做考虑，因此情况3只会拿本来就属于自己仓库的桶
    for (int i = 0; i < buckets1.size(); i++) {
        for (int j = i + 1; j < buckets1.size(); j++) {
            for (int u = 0; u < buckets2.size(); u++) {
                for (int v = u + 1; v < buckets2.size(); v++) {
                    milks.insert(buckets2[u] + buckets2[v] - buckets1[i] - buckets1[j]);
                }
            }
        }
    }

    fout << milks.size() << endl;

    fin.close();
    fout.close();

    return 0;
}
