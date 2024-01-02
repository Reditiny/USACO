#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

int N;
vector<int> cow_location;
vector<int> pass_distance;
// 找到最后一头牛的索引
int find_last_index(int value){
    int index = 0;
    for (int i = 0; i < pass_distance.size(); i++) {
        if (pass_distance[i] == value) {
            index = i;
        }
    }
    return index;
}

int main() {
    ifstream fin("hoofball.in");
    ofstream fout("hoofball.out");

    fin >> N;

    for (int i = 0; i < N; i++) {
        int loc;
        fin >> loc;
        cow_location.push_back(loc);
    }

    // 按位置升序排序 并找到每头牛传球的距离
    sort(cow_location.begin(), cow_location.end());
    pass_distance.push_back(cow_location[1] - cow_location[0]);
    for (int i = 1; i < N - 1; i++) {
        pass_distance.push_back(min(cow_location[i] - cow_location[i - 1], cow_location[i + 1] - cow_location[i]));
    }
    pass_distance.push_back(cow_location[N - 1] - cow_location[N - 2]);

    int ans = 0;
    while (true) {
        // 找到最后一头当前传球距离最远的牛，这头牛作为起点，因为它的传球距离最远，它不会接到其他牛的传球
        int max_distance = *max_element(pass_distance.begin(), pass_distance.end());
        if (max_distance == -1) {
            break;
        }
        // 为什么是 lastIndexOf 而不是 indexOf
        // 对于相同的距离，右边的牛总会传给左边的牛，所以起始点应该是最右边的牛
        int max_distance_index = find_last_index(max_distance);
        ans += 1;
        // 传球直到球不再传给新牛
        while (true) {
            int next_index = 0;
            if (pass_distance[max_distance_index] == -1) {
                break;
            }
            if (max_distance_index == 0) {
                next_index = max_distance_index + 1;
            } else if (max_distance_index == N - 1) {
                next_index = max_distance_index - 1;
            } else {
                if (cow_location[max_distance_index] - cow_location[max_distance_index - 1] <= cow_location[max_distance_index + 1] - cow_location[max_distance_index]) {
                    next_index = max_distance_index - 1;
                } else {
                    next_index = max_distance_index + 1;
                }
            }
            pass_distance[max_distance_index] = -1;
            max_distance_index = next_index;
        }
    }

    fout << ans << endl;

    fin.close();
    fout.close();

    return 0;
}
