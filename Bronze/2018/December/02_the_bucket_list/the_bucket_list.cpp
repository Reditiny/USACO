#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;

int main() {
    ifstream fin("blist.in");
    ofstream fout("blist.out");
    // 记录每单位时间的桶数 时间粒度为 1
    int bucket_count[1001] = {0};
    int n;
    fin >> n;
    // 每只牛开始时间到结束时间的区间，记录每单位时间的桶数
    int max_buckets = 0;
    for (int i = 0; i < n; i++) {
        int start, end, count;
        fin >> start >> end >> count;
        for (int j = start; j <= end; j++) {
            bucket_count[j] += count;
            max_buckets = max(max_buckets, bucket_count[j]);
        }
    }

    fout << max_buckets << endl;

    fin.close();
    fout.close();

    return 0;
}
