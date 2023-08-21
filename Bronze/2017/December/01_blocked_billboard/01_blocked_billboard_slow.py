"""
ID: mck15821
LANG: PYTHON3
PROG: billboard
"""
fin = open('billboard.in', 'r')
fout = open("billboard.out", "w")

visibility = []
MAX_POS = 2000
for i in range(MAX_POS):
    visibility.append([False for j in range(MAX_POS)])

for i in range(3):
    x1, y1, x2, y2 = map(int, fin.readline().strip().split())
    x1 += MAX_POS // 2
    y1 += MAX_POS // 2
    x2 += MAX_POS // 2
    y2 += MAX_POS // 2
    for x in range(x1, x2):
        for y in range(y1, y2):
            visibility[x][y] = i < 2

area = 0
for i in range(MAX_POS):
    for j in range(MAX_POS):
        if visibility[i][j]:
            area += 1

fout.write(f"{area}\n")
fout.close()
