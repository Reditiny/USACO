#include <iostream>
#include <fstream>
#include <vector>

using namespace std;
// C++ 中不支持用变量指定数组大小，所以此处需要使用 const
const int bucket_count = 3;
const int cycle_pour_times = 33;
int bucket_capacity[bucket_count];
int bucket_init_milk[bucket_count];
int bucket_cur_milk[bucket_count];
// 每个桶都倒一次
void pour3Times() {
    for (int i = 0; i < bucket_count; i++) {
        int nextBucket = (i + 1) % bucket_count;
        int pour_milk = min(bucket_cur_milk[i], bucket_capacity[nextBucket] - bucket_cur_milk[nextBucket]);
        bucket_cur_milk[i] -= pour_milk;
        bucket_cur_milk[nextBucket] += pour_milk;
    }
}
// 最后倒一次
void pour() {
    int pour_milk = min(bucket_cur_milk[0], bucket_capacity[1] - bucket_cur_milk[1]);
    bucket_cur_milk[0] -= pour_milk;
    bucket_cur_milk[1] += pour_milk;
}
// 判断当前桶的牛奶是否与初始状态相同
bool matchInit() {
    for (int i = 0; i < bucket_count; i++) {
        if (bucket_cur_milk[i] != bucket_init_milk[i]) {
            return false;
        }
    }
    return true;
}

int main() {
    ifstream fin("mixmilk.in");
    ofstream fout("mixmilk.out");

    for (int i = 0; i < bucket_count; i++) {
        fin >> bucket_capacity[i] >> bucket_init_milk[i];
        bucket_cur_milk[i] = bucket_init_milk[i];
    }
    // 寻找周期 即经过 cycle 次操作后，序列回到原来的状态
    int cycle = 0;
    while (true) {
        pour3Times();
        cycle++;
        if (cycle == cycle_pour_times || matchInit()) {
            break;
        }
    }
    // 最后仅需要进行 k % cycle + 1 次操作即可
    int pour_times = cycle_pour_times % cycle;
    for (int i = 0; i < pour_times; i++) {
        pour3Times();
    }
    pour();

    for (int i = 0; i < bucket_count; i++) {
        fout << bucket_cur_milk[i] << endl;
    }

    fin.close();
    fout.close();

    return 0;
}
