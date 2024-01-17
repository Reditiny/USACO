#include <iostream>
#include <fstream>
#include <climits>
using namespace std;

const int MAX_X = 1000000;
int N, A[MAX_X+1]; // 1=healthy, -1=sick, 0=no cow
// R 越大，最开始生病的牛就越少
// R 的值为病牛与健康牛之间的最小距离
int get_max_r(){
    int r = INT_MAX, current_start = -1;
    for (int i=0; i<=MAX_X; i++)
        if (A[i] != 0) {
            if (current_start!=-1 && A[current_start]!=A[i] && i-current_start < r)
                r = i - current_start;
            current_start = i;
        }
    return r;
}
// 被健康牛分割的病牛群的数量
// 每个病牛群最开始至少有一头病牛
int num_sick_clusters(){
    int last_state = 0, answer = 0;
    for (int i=0; i<=MAX_X; i++)
        if (A[i] != 0) {
            if (A[i] != last_state && A[i] == 1) answer++;
            last_state = A[i];
        }
    return answer;
}
// 当相邻的病牛之间的距离大于等于 R 时，就把他们分开，记录分开的次数
// 把当前病牛群从此处分开，分成两个病牛群，因为这两个群没法传播病毒
int divide_sick_count(int r){
    int answer = 0, current_start = 0;
    for (int i=0; i<=MAX_X; i++)
        if (A[i] != 0) {
            if (current_start!=0 && A[current_start]==1 && A[i]==1 && i-current_start>=r)
                answer++;
            current_start = i;
        }
    return answer;
}

int main(void)
{
    ifstream fin ("socdist2.in");
    ofstream fout ("socdist2.out");
    int x, s;
    fin >> N;
    for (int i=0; i<N; i++) {
        fin >> x >> s;
        if (s==1) { A[x] = 1; }
        if (s==0) { A[x] = -1; }
    }
    int r = get_max_r();
    fout << num_sick_clusters() + divide_sick_count(r) << "\n";
    return 0;
}