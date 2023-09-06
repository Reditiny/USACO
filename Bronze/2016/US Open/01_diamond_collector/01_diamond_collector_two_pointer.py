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
# two pointer
i = 0
j = 1
while j < N:
    while j < N and diamonds[i] + K >= diamonds[j]:
        result = max(result, j - i + 1)
        j += 1
    while i < j < N and diamonds[i] + K < diamonds[j]:
        i += 1

fout.write(f"{result}\n")
fout.close()
