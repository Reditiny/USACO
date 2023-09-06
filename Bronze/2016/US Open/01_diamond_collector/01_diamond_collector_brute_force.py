"""
ID: mck15821
LANG: PYTHON3
PROG: diamond
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=639
fin = open('diamond.in', 'r')
fout = open("diamond.out", "w")

N, K = map(int, fin.readline().strip().split())
diamonds = []
for _ in range(N):
    diamonds.append(int(fin.readline().strip()))
diamonds.sort()

result = 0
# brute force
for i in range(N):
    j2 = i + 1
    while j2 < N and diamonds[i] + K >= diamonds[j2]:
        j2 += 1
    count = j2 - i
    result = max(result, count)

fout.write(f"{result}\n")
fout.close()
