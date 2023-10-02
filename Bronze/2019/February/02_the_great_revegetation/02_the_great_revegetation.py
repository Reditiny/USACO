"""
ID: mck15821
LANG: PYTHON3
PROG: revegetate
"""
fin = open('revegetate.in', 'r')
fout = open("revegetate.out", "w")
# http://usaco.org/index.php?page=viewproblem2&cpid=916

N, K = map(int, fin.readline().strip().split())

neighbors = [[] for _ in range(N)]
for _ in range(K):
    a, b = map(int, fin.readline().strip().split())
    neighbors[a - 1].append(b - 1)
    neighbors[b - 1].append(a - 1)

color = [0 for _ in range(N)]
for i in range(N):
    for c in range(1, 5):
        can_use = True
        for neighbor in neighbors[i]:
            if color[neighbor] == c:
                can_use = False
        if can_use:
            break
    color[i] = c
    fout.write(str(c))
fout.close()
