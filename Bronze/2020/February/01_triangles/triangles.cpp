#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;
// 获得三角形面积(x1,y1)(x2,y2)(x3,y3) 输出 0 表示三点不符合条件
// x1 == x2 时需要有 y3 == y2 || y3 == y1
// x3 == x2 时需要有 y1 == y2 || y1 == y3
int get_area(const vector<int>& point1, const vector<int>& point2, const vector<int>& point3) {
    int dx = 0, dy = 0;
    if (point1[0] == point2[0]) {
        dy = abs(point2[1] - point1[1]);
        if (point2[1] == point3[1] || point1[1] == point3[1]) {
            dx = abs(point1[0] - point3[0]);
        }
    } else if (point3[0] == point2[0]) {
        dy = abs(point3[1] - point2[1]);
        if (point3[1] == point1[1] || point2[1] == point1[1]) {
            dx = abs(point2[0] - point1[0]);
        }
    }
    return dx * dy;
}

int main() {
    ifstream fin("triangles.in");
    ofstream fout("triangles.out");

    int n;
    fin >> n;

    vector<vector<int>> points(n, vector<int>(2));
    for (int i = 0; i < n; i++) {
        fin >> points[i][0] >> points[i][1];
    }
    // 按照 x 坐标排序
    sort(points.begin(), points.end(), [](const vector<int>& a, const vector<int>& b) {
        return a[0] < b[0];
    });

    int ans = 0;
    // 枚举所有可能的三角形 一边与x轴平行 一边与y轴平行
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            for (int k = j + 1; k < n; k++) {
                ans = max(ans, get_area(points[i], points[j], points[k]));
            }
        }
    }

    fout << ans << endl;

    fin.close();
    fout.close();

    return 0;
}
