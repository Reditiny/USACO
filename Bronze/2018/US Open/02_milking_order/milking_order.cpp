#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

// index 为顺序位置 [index] 为牛的编号 用于寻找空位以及最后输出
vector<int> position_to_cow;
vector<int> cow_hierarchy;
int n;
// index 为牛的编号 [index] 为该牛要求的顺序位置 [index] == 0 表示该牛没有要求顺序位置
// 用于快速查找牛所要求的顺序位置
vector<int> cow_to_position;

// 将 Hierarchy 中 1 前面的牛从前往后依次放入队列中
// 返回值为 1 前面最后一个牛的位置
int put_prefix_hierarchy() {
    int last_position = 1;
    for (int i = 0; i < cow_hierarchy.size(); i++) {
        int curCow = cow_hierarchy[i];
        if (cow_to_position[curCow] != 0) {
            last_position = cow_to_position[curCow];
        } else {
            while (position_to_cow[last_position] != 0) {
                last_position++;
            }
        }
        if (curCow == 1) {
            break;
        }
        position_to_cow[last_position] = curCow;
    }
    return last_position;
}
// 将 Hierarchy 中所有牛从后往前依次放入队列中
void put_all_hierarchy() {
    int last_position = n;
    for (int i = cow_hierarchy.size() - 1; i >= 0; i--) {
        int cur_cow = cow_hierarchy[i];
        if (cow_to_position[cur_cow] != 0) {
            last_position = cow_to_position[cur_cow];
        } else {
            while (position_to_cow[last_position] != 0) {
                last_position--;
            }
        }
        position_to_cow[last_position] = cur_cow;
    }
}

int main() {
    ifstream fin("milkorder.in");
    ofstream fout("milkorder.out");

    fin >> n;
    int m, k;
    fin >> m >> k;

    // 这里额外多申请一个空间是为了让默认值 0 表示该牛没有要求顺序位置
    cow_to_position.resize(n + 1, 0);
    position_to_cow.resize(n + 1, 0);
    cow_hierarchy.resize(m);

    bool hierarchyContainCow1 = false;
    for (int i = 0; i < m; i++) {
        fin >> cow_hierarchy[i];
        if (cow_hierarchy[i] == 1) {
            hierarchyContainCow1 = true;
        }
    }

    for (int i = 0; i < k; i++) {
        int cow, position;
        fin >> cow >> position;
        position_to_cow[position] = cow;
        cow_to_position[cow] = position;
    }

    int lastPosition = 1;

    // hierarchy 中是否包含 cow1 会有不同的处理方案
    if (hierarchyContainCow1) {
        lastPosition = put_prefix_hierarchy();
    } else {
        put_all_hierarchy();
    }

    for (int i = lastPosition; i <= n; i++) {
        if (position_to_cow[i] == 0) {
            fout << i << endl;
            break;
        }
    }

    fin.close();
    fout.close();

    return 0;
}
