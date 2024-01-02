#include <iostream>
#include <fstream>

using namespace std;

int bucket_capacity[3];
int bucket_cur_milk[3];

int main() {
    ifstream fin("mixmilk.in");
    ofstream fout("mixmilk.out");

    for (int i = 0; i < 3; i++) {
        fin >> bucket_capacity[i] >> bucket_cur_milk[i];
    }
    // 倒 100  次牛奶
    for (int i = 0; i < 100; i++) {
        int from = i % 3;
        int to = (i + 1) % 3;
        int pour_milk = min(bucket_cur_milk[from], bucket_capacity[to] - bucket_cur_milk[to]);
        bucket_cur_milk[from] -= pour_milk;
        bucket_cur_milk[to] += pour_milk;
    }

    for (int i = 0; i < 3; i++) {
        fout << bucket_cur_milk[i] << endl;
    }

    fin.close();
    fout.close();

    return 0;
}
