#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int N;
vector<int> cow_positions;
string stalls;
// 检查当前 d 是否可以插入两头牛
bool check(int d) {
    int new_cow = 2;
    // 单独检查前缀能插入几头牛 前缀只有一边有牛
    int first_distance = cow_positions[0];
    if (first_distance >= 2 * d) {
        new_cow -= 2;
    } else if (first_distance >= d) {
        new_cow -= 1;
    }
    // 检查中间部分能插入几头牛同时注意本身距离也要满足条件 中间部分两边都有牛
    for (int i = 1; i < cow_positions.size(); i++) {
        int distance = cow_positions[i] - cow_positions[i - 1];
        if (distance >= 3 * d) {
            new_cow -= 2;
        } else if (distance >= 2 * d) {
            new_cow -= 1;
        } else if (distance < d) {
            return false;
        }
    }
    // 单独检查后缀能插入几头牛 后缀只有一边有牛
    int last_distance = N - 1 - cow_positions[cow_positions.size() - 1];
    if (last_distance >= 2 * d) {
        new_cow -= 2;
    } else if (last_distance >= d) {
        new_cow -= 1;
    }

    return new_cow <= 0;
}

int main() {
    ifstream fin("socdist1.in");
    ofstream fout("socdist1.out");

    fin >> N;
    fin >> stalls;

    for (int i = 0; i < N; i++) {
        if (stalls[i] == '1') {
            cow_positions.push_back(i);
        }
    }

    if (cow_positions.size() == 0) {
        fout << N - 1 << endl;
        return 0;
    }

    int left = 0, right = N;
    // 二分查找最大的 d
    while (left < right) {
        int mid = (left + right + 1) / 2;
        if (check(mid)) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }

    fout << left << endl;

    fin.close();
    fout.close();

    return 0;
}
