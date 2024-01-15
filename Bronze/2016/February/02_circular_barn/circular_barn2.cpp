#include <iostream>
#include <fstream>
#include <vector>
#include <climits>

using namespace std;

int main() {
    ifstream fin("cbarn.in");
    ofstream fout("cbarn.out");

    int N;
    fin >> N;

    vector<int> rooms(N);

    for (int i = 0; i < N; i++) {
        fin >> rooms[i];
    }

    int result = INT_MAX;

    // 以每个房间为起点，计算总距离
    for (int i = 0; i < N; i++) {
        int acc = 0;
        for (int j = 0; j < N; j++) {
            acc += rooms[(i + j) % N] * j;
        }
        result = min(result, acc);
    }

    fout << result << endl;
    fout.close();

    return 0;
}
