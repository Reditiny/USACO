"""
ID: mck15821
LANG: PYTHON3
PROG: Triangles
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=1011
fin = open('triangles.in', 'r')
fout = open("triangles.out", "w")
N = int(fin.readline().strip())
points = []
for _ in range(N):
    points.append(list(map(int, fin.readline().strip().split())))

points.sort(key=lambda x: x[0])

max_value = 0
for i in range(N):
    for j in range(i + 1, N):
        for k in range(j + 1, N):
            # first 2 points have the same x
            if points[i][0] == points[j][0] and points[k][0] != points[i][0]:
                # third point have the same y with one of the point
                if points[i][1] == points[k][1] or points[j][1] == points[k][1]:
                    max_value = max(max_value, abs(points[j][1] - points[i][1]) * (points[k][0] - points[i][0]))
            # second and third have the same x
            if points[i][0] != points[j][0] and points[j][0] == points[k][0]:
                # first point have the same y with one of the point
                if points[i][1] == points[j][1] or points[i][1] == points[k][1]:
                    max_value = max(max_value, abs(points[j][1] - points[k][1]) * (points[k][0] - points[i][0]))

fout.write(f"{max_value}\n")
fout.close()
