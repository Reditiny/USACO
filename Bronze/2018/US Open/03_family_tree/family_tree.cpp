#include <iostream>
#include <fstream>
#include <vector>
#include <unordered_map>
#include <algorithm>

using namespace std;

unordered_map<string, string> cow_to_mother_cow;
vector<string> cow1_ancestors;
vector<string> cow2_ancestors;

int main() {
    ifstream fin("family.in");
    ofstream fout("family.out");

    int n;
    fin >> n;

    string cow1, cow2;
    fin >> cow1 >> cow2;

    for (int i = 0; i < n; i++) {
        string mother_cow, cow;
        fin >> mother_cow >> cow;
        // 记录子牛->母牛的映射关系 便于后续寻找祖先
        cow_to_mother_cow[cow] = mother_cow;
    }
    // 从 cow1 开始依次记录祖先(包括自己)
    string cur_cow = cow1;
    cow1_ancestors.push_back(cow1);
    while (cow_to_mother_cow.count(cur_cow)) {
        string mother = cow_to_mother_cow[cur_cow];
        cow1_ancestors.push_back(mother);
        cur_cow = mother;
    }

    // 从 cow2 开始依次记录祖先(包括自己)
    cur_cow = cow2;
    cow2_ancestors.push_back(cow2);
    while (cow_to_mother_cow.count(cur_cow)) {
        string mother = cow_to_mother_cow[cur_cow];
        cow2_ancestors.push_back(mother);
        cur_cow = mother;
    }
    // 检查是否有共同祖先并记录距离的代数
    int cow1_step = 0, cow2_step = 0;
    for (int i = 0; i < cow1_ancestors.size(); i++) {
        if (find(cow2_ancestors.begin(), cow2_ancestors.end(), cow1_ancestors[i]) != cow2_ancestors.end()) {
            cow1_step = i;
            cow2_step = distance(cow2_ancestors.begin(), find(cow2_ancestors.begin(), cow2_ancestors.end(), cow1_ancestors[i]));
            break;
        }
    }
    // 根据代数关系输出结果
    if (cow1_step == 0 && cow2_step == 0) {
        // 1.没有共同祖先说明没有关系
        fout << "NOT RELATED" << endl;
    } else if (cow1_step >= 2 && cow2_step >= 2) {
        // 2.两者离共同祖先都相距很远
        fout << "COUSINS" << endl;
    } else if (cow1_step == 1 && cow2_step == 1) {
        // 3.同一个妈妈
        fout << "SIBLINGS" << endl;
    } else if (cow1_step == 0 || cow2_step == 0) {
        // 4.直接祖先
        // 先确定谁是祖先
        string ancestor_cow, cow;
        if (cow1_step == 0) {
            ancestor_cow = cow1;
            cow = cow2;
        } else {
            ancestor_cow = cow2;
            cow = cow1;
        }
        // 根据相隔的代数输出结果
        int diff = abs(cow1_step - cow2_step);
        fout << ancestor_cow << " is the ";
        while (diff >= 3) {
            fout << "great-";
            diff--;
        }
        while (diff >= 2) {
            fout << "grand-";
            diff--;
        }
        fout << "mother of " << cow << endl;
    } else {
        // 5.长少关系
        // 确定谁是长辈
        string ancestor_cow, cow;
        if (cow1_step < cow2_step) {
            ancestor_cow = cow1;
            cow = cow2;
        } else {
            ancestor_cow = cow2;
            cow = cow1;
        }
        // 根据相隔的代数输出结果
        int diff = abs(cow1_step - cow2_step);
        fout << ancestor_cow << " is the ";
        while (diff >= 2) {
            fout << "great-";
            diff--;
        }
        fout << "aunt of " << cow << endl;
    }

    fin.close();
    fout.close();

    return 0;
}
