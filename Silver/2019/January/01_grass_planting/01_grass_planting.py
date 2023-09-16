"""
ID: mck15821
LANG: PYTHON3
PROG: planting
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=894
fin = open('planting.in', 'r')
fout = open("planting.out", "w")

N = int(fin.readline().strip())
graph = [[] for _ in range(N)]

for _ in range(N):
    a, b = map(int, fin.readline().strip().split())
    graph[a - 1].append(b - 1)
    graph[b - 1].append(a - 1)

result = N


def dfs()


fout.close()
