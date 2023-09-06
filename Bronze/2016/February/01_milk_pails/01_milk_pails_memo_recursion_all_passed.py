"""
ID: mck15821
LANG: PYTHON3
PROG: pails
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=615
fin = open('pails.in', 'r')
fout = open("pails.out", "w")

X, Y, M = map(int, fin.readline().strip().split())
result = [0]
cache = [False for _ in range(M + 1)]


def dfs(acc):
    if acc > M or cache[acc] is True:
        return
    cache[acc] = True
    result[0] = max(result[0], acc)
    dfs(acc + X)
    dfs(acc + Y)


dfs(0)
fout.write(f"{result[0]}\n")
fout.close()
