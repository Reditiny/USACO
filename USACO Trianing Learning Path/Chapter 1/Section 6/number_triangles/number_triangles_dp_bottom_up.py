"""
ID: mck15821
LANG: PYTHON3
PROG: numtri
"""

fin = open('numtri.in', 'r')
fout = open("numtri.out", "w")
R = int(fin.readline().strip())
triangles = []
for i in range(R):
    triangles.append(list(map(int, fin.readline().strip().split())))

for i in range(R - 1, 0, -1):
    for j in range(i):
        triangles[i - 1][j] += max(triangles[i][j], triangles[i][j + 1])

fout.write(f"{triangles[0][0]}\n")
fout.close()