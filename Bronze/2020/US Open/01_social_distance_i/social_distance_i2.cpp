#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

// 找到两个 1 之间最大的间隔
pair<int, int> find_largest_interior_gap(const string& s) {
    int gap_start = -1;
    int biggest_gap = 0;
    int current_start = -1;

    for (int i = 0; i < s.length(); i++) {
        if (s[i] == '1') {
            if (current_start != -1 && i - current_start > biggest_gap) {
                biggest_gap = i - current_start;
                gap_start = current_start;
            }
            current_start = i;
        }
    }

    return {biggest_gap, gap_start};
}

// 找到两个 1 之间最小的间隔
int find_smallest_interior_gap(const string& s) {
    int smallest_gap = s.length() + 1;
    int current_start = -1;

    for (int i = 0; i < s.length(); i++) {
        if (s[i] == '1') {
            if (current_start != -1 && i - current_start < smallest_gap) {
                smallest_gap = i - current_start;
            }
            current_start = i;
        }
    }

    return smallest_gap;
}

// 在最大间隔中尝试放置一头牛
int try_cow_in_largest_gap(string& s) {
    pair<int,int>  biggest_gap_and_start = find_largest_interior_gap(s);
    int biggest_gap = biggest_gap_and_start.first;
    int biggest_gap_start = biggest_gap_and_start.second;
    if (biggest_gap >= 2) {
        s[biggest_gap_start + biggest_gap / 2] = '1';
        return find_smallest_interior_gap(s);
    }

    return -1;  // 没有间隔
}

int main() {
    ifstream fin("socdist1.in");
    ofstream fout("socdist1.out");

    int N;
    fin >> N;

    string s;
    fin >> s;

    int answer = 0;

    // 可能性1：两头牛放在最大间隔中
    pair<int,int>  biggest_gap_and_start = find_largest_interior_gap(s);
    int biggest_gap = biggest_gap_and_start.first;
    int biggest_gap_start = biggest_gap_and_start.second;
    if (biggest_gap >= 3) {
        string temp_s = s;
        temp_s[biggest_gap_start + biggest_gap / 3] = '1';
        temp_s[biggest_gap_start + biggest_gap * 2 / 3] = '1';
        answer = max(answer, find_smallest_interior_gap(temp_s));
    }

    // 可能性2：两边都放牛
    if (s[0] == '0' && s[N - 1] == '0') {
        string temp_s = s;
        temp_s[0] = '1';
        temp_s[N - 1] = '1';
        answer = max(answer, find_smallest_interior_gap(temp_s));
    }

    // 可能性3：左边放牛 + 最大间隔中放牛
    if (s[0] == '0') {
        string temp_s = s;
        temp_s[0] = '1';
        answer = max(answer, try_cow_in_largest_gap(temp_s));
    }

    // 可能性4：右边放牛 + 最大间隔中放牛
    if (s[N - 1] == '0') {
        string temp_s = s;
        temp_s[N - 1] = '1';
        answer = max(answer, try_cow_in_largest_gap(temp_s));
    }

    // 可能性5：最大间隔中放牛，两次
    if (biggest_gap >= 2) {
        string temp_s = s;
        temp_s[biggest_gap_start + biggest_gap / 2] = '1';
        answer = max(answer, try_cow_in_largest_gap(temp_s));
    }

    fout << answer << endl;
    return 0;
}
