#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <unordered_set>

using namespace std;

vector<int> buckets1;
vector<int> buckets2;
unordered_set<int> milks;
// 递归移动
void move(vector<int> milk_mount, vector<int>& buckets_from, vector<int>& buckets_to) {
    if (milk_mount.size() == 4) {
        int mount = 1000;
        mount -= (milk_mount[0] + milk_mount[2]);
        mount += (milk_mount[1] + milk_mount[3]);
        milks.insert(mount);
        return;
    }
    for (int i = 0; i < buckets_from.size(); i++) {
        vector<int> buckets_from_copy = buckets_from;
        vector<int> buckets_to_copy = buckets_to;
        vector<int> milk_mount_copy = milk_mount;
        int d = buckets_from_copy[i];
        buckets_from_copy.erase(buckets_from_copy.begin() + i);
        buckets_to_copy.push_back(d);
        milk_mount_copy.push_back(d);
        move(milk_mount_copy, buckets_to_copy, buckets_from_copy);
    }
}

int main() {
    ifstream fin("backforth.in");
    ofstream fout("backforth.out");

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

    move({}, buckets1, buckets2);

    fout << milks.size() << endl;

    fin.close();
    fout.close();

    return 0;
}
