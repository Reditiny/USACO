#include <iostream>
#include <fstream>

using namespace std;

int X, Y, M;
int result = 0;

void dfs(int acc) {
    if (acc > M) {
        return;
    }
    result = max(result, acc);
    dfs(acc + X);
    dfs(acc + Y);
}

int main() {
    ifstream fin("pails.in");
    ofstream fout("pails.out");

    fin >> X >> Y >> M;

    dfs(0);

    fout << result << endl;
    fout.close();

    return 0;
}
