"""
ID: mck15821
LANG: PYTHON3
PROG: cownomics
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=736
fin = open('cownomics.in', 'r')
fout = open("cownomics.out", "w")

N, M = map(int, fin.readline().strip().split())
genes = []
for i in range(2 * N):
    genes.append(fin.readline().strip())

count = 0
for i in range(M):
    spotted = set()
    found = False
    for j in range(N):
        spotted.add(genes[j][i])
    # if character existed in spotted, and also in unspotted, then break
    for j in range(N, 2 * N):
        if genes[j][i] in spotted:
            found = True
            break
    if not found:
        count += 1

fout.write(f"{count}\n")
fout.close()
