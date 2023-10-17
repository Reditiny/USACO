"""
ID: mck15821
LANG: PYTHON3
PROG: crossroad
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=711
fin = open('crossroad.in', 'r')
fout = open("crossroad.out", "w")
N = int(fin.readline().strip())
records = []
for _ in range(N):
    records.append(list(map(int, fin.readline().strip().split())))

visited = set()
counter = 0
for i in range(N):
    cur = records[i]
    if cur[0] in visited:
        continue
    last_pos = cur[1]
    for j in range(i + 1, N):
        if records[j][0] == cur[0] and records[j][1] != last_pos:
            counter += 1
            last_pos = 1 - last_pos
    visited.add(cur[0])

fout.write(f"{counter}")
fout.close()
