#include <iostream>
#include <fstream>

using namespace std;

int k;
/**
 * 计算最大速度 maxSpeed 时花费最少时间
 * 最优策略开始应该尽可能的加速，最后再减速至 maxSpeed
 *         ----
 *       -     - maxSpeed
 *     -
 *   - 0         速度变化趋势
 */
int run(int maxSpeed) {
    // 分别计算加速过程和减速过程的距离
    int speed_up_dist = 0;
    int slow_down_dist = 0;
    int time = 0;
    // 一直加速，同时计算加速与减速的距离，直到总距离超过 k
    for (int curr_speed = 1;; curr_speed++) {
        speed_up_dist += curr_speed;
        time++;
        if (speed_up_dist + slow_down_dist >= k) {
            return time;
        }
        // 当前速度超过 maxSpeed 时，后续一定会经过减速阶段
        if (curr_speed >= maxSpeed) {
            slow_down_dist += curr_speed;
            time++;

            if (speed_up_dist + slow_down_dist >= k) {
                return time;
            }
        }
    }
}

int main() {
    ifstream fin("race.in");
    ofstream fout("race.out");

    int n;
    fin >> k >> n;

    for (int i = 0; i < n; i++) {
        int x;
        fin >> x;
        fout << run(x) << endl;
    }

    fin.close();
    fout.close();

    return 0;
}
