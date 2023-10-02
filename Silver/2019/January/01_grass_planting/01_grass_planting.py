"""
ID: mck15821
LANG: PYTHON3
PROG: planting
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=894
fin = open('planting.in', 'r')
fout = open("planting.out", "w")

N = int(fin.readline().strip())
degree = [0 for _ in range(N)]

for i in range(N - 1):
    a, b = map(int, fin.readline().strip().split())
    degree[a - 1] += 1
    degree[b - 1] += 1

fout.write(f"{max(degree) + 1}")
fout.close()
