#include <iostream>
#include <fstream>
#include <cmath>

using namespace std;

int main() {
    freopen("lostcow.in", "r", stdin);
    freopen("lostcow.out", "w", stdout);

    int farmer_position, cow_position;
    cin >> farmer_position >> cow_position;
    // 每次往右的跨度为 2 的偶次幂   往左跨度为 2 的奇次幂
    if (cow_position > farmer_position) {
        // 依次探查往右的最大距离直到可以发现目标
        int step = 1;
        int power = 0;
        int current_position = farmer_position + 1;
        while (cow_position > current_position) {
            // 本次往右没有发现目标 则先回到原点 再往左走 再回到原点 准备下次往右
            step += pow(2, power) + 2 * pow(2, power + 1);
            power += 2;
            step += pow(2, power);
            current_position = farmer_position + static_cast<int>(pow(2, power));
        }
        step -= (current_position - cow_position);
        cout << step << endl;
    } else {
        int step = 4;   // 从原点先向右1步，回到原点1步，再向左2步
        int power = 1;
        int currentPosition = farmer_position - 2;
        while (cow_position < currentPosition) {
            step += pow(2, power) + 2 * pow(2, power + 1);
            power += 2;
            step += pow(2, power);
            currentPosition = farmer_position - static_cast<int>(pow(2, power));
        }
        step -= (cow_position - currentPosition);
        cout << step << endl;
    }
    return 0;
}
