"""
ID: mck15821
LANG: PYTHON3
PROG: bcs
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=640
fin = open('bcs.in', 'r')
fout = open("bcs.out", "w")

N, K = map(int, fin.readline().strip().split())

pieces = []
sides = []
for i in range(K + 1):
    top = N - 1
    bottom = 0
    left = N - 1
    right = 0
    piece = [[False for _ in range(N)] for _ in range(N)]

    for r in range(N):
        row = fin.readline().strip()
        for c in range(N):
            piece[r][c] = row[c] == "#"
            if piece[r][c] is True:
                top = min(top, r)
                bottom = max(bottom, r)
                left = min(left, c)
                right = max(right, c)
    pieces.append(piece)
    sides.append((top, bottom, left, right))

target = pieces[0]

for i in range(1, K + 1):
    for j in range(1, K + 1):
        if i == j:
            continue
        for x_shift in range()



fout.write(f"{result}\n")
fout.close()
