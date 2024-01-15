#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ifstream fin("diamond.in");
    ofstream fout("diamond.out");

    int N, K;
    fin >> N >> K;

    vector<int> diamonds(N);
    for (int i = 0; i < N; i++) {
        fin >> diamonds[i];
    }
    sort(diamonds.begin(), diamonds.end());

    int result = 0;
    int i = 0, j = 1;
    while (j < N) {
        while (j < N && diamonds[i] + K >= diamonds[j]) {
            result = max(result, j - i + 1);
            j++;
        }
        while (i < j && j < N && diamonds[i] + K < diamonds[j]) {
            i++;
        }
    }

    fout << result << endl;
    fout.close();

    return 0;
}
