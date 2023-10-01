"""
ID: mck15821
LANG: PYTHON3
PROG: art
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=737
fin = open('art.in', 'r')
fout = open("art.out", "w")

N = int(fin.readline().strip())
arts = []

for i in range(N):
    line = fin.readline().strip()
    arts.append([int(c) for c in line])

coordinates = []
for i in range(10):
    coordinates.append([N, N, -1, -1])

for i in range(N):
    for j in range(N):
        digit = arts[i][j]
        if digit > 0:
            coordinates[digit][0] = min(coordinates[digit][0], i)
            coordinates[digit][1] = min(coordinates[digit][1], j)
            coordinates[digit][2] = max(coordinates[digit][2], i)
            coordinates[digit][3] = max(coordinates[digit][3], j)

result = 0
not_originals = [0] * 10
for color in range(10):
    if coordinates[color][0] != N:
        result += 1
        for i in range(coordinates[color][0], coordinates[color][2] + 1):
            for j in range(coordinates[color][1], coordinates[color][3] + 1):
                if arts[i][j] != color:
                    not_originals[arts[i][j]] = 1

fout.write(f"{result - sum(not_originals)}\n")
fout.close()
