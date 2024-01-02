#include <iostream>
#include <fstream>
#include <cstdlib>
#include <algorithm>

using namespace std;



int main() {
    ifstream fin("teleport.in");
    ofstream fout("teleport.out");
    int a, b, x, y;
    fin >> a >> b >> x >> y;

    // 三种情况
    // 1.不使用传送门
    int ans = abs(a - b);
    // 2.使用传送门 x -> y
    ans = min(ans, abs(a - x) + abs(b - y));
    // 3.使用传送门 y -> x
    ans = min(ans, abs(a - y) + abs(b - x));

    fout << ans << endl;

    fin.close();
    fout.close();

    return 0;
}
