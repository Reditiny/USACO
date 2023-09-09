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

for i in range(N):
    for j in range(i + 1, N):
        for k in range(j + 1, N):
            if points[i][0] == points[j][0] and points[k][]
