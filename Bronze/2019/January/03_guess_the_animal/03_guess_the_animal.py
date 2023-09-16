"""
ID: mck15821
LANG: PYTHON3
PROG: guess
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=893
fin = open('guess.in', 'r')
fout = open("guess.out", "w")

N, M = list(map(int, fin.readline().strip().split()))
genes = []
for _ in range(2 * N):
    genes.append(fin.readline().strip())

count = 0
for i in range(M):
    for j in range(i + 1, M):
        for k in range(j + 1, M):
            spotted_set = set()
            for r in range(N):
                spotted_set.add(genes[r][i] + genes[r][j] + genes[r][k])
            duplicated = False
            for r in range(N, 2 * N):
                if genes[r][i] + genes[r][j] + genes[r][k] in spotted_set:
                    duplicated = True
                    break
            if duplicated is False:
                count += 1

fout.write(f"{count}\n")
fout.close()
