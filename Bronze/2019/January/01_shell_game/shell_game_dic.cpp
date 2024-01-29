#include <iostream>
#include <fstream>
#include <vector>
#include <map>

using namespace std;

int main() {
    ifstream fin("shell.in");
    ofstream fout("shell.out");

    int N;
    fin >> N;

    vector<vector<int>> rounds(N, vector<int>(3));

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < 3; j++) {
            fin >> rounds[i][j];
        }
    }

    vector<string> shellIds = {"", "id1", "id2", "id3"};
    map<string, int> scores = {{"id1", 0}, {"id2", 0}, {"id3", 0}};
    int max_count = 0;
    // 交换 round[0] 与 round[1] 并更新分数
    for (const vector<int>& r : rounds) {
        string temp = shellIds[r[0]];
        shellIds[r[0]] = shellIds[r[1]];
        shellIds[r[1]] = temp;

        scores[shellIds[r[2]]]++;
        max_count = max(max_count, scores[shellIds[r[2]]]);
    }
    fout << max_count << endl;

    return 0;
}
