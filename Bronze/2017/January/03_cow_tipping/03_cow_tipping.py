"""
ID: mck15821
LANG: PYTHON3
PROG: cowtip
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=689
fin = open('cowtip.in', 'r')
fout = open("cowtip.out", "w")
N = int(fin.readline().strip())

matrix = []
for _ in range(N):
    line = fin.readline().strip()
    temp = []
    for c in line:
        temp.append(int(c))
    matrix.append(temp)


def flip(x, y):
    for i in range(x + 1):
        for j in range(y + 1):
            matrix[i][j] = 1 - matrix[i][j]

print(matrix)
count = 0
# start from the right bottom corner, than scan from right to left
# after finishing the last line, get one line above
for i in range(N - 1, -1, -1):
    for j in range(N - 1, -1, -1):
        if matrix[i][j] == 1:
            flip(i, j)
            count += 1

fout.write(f"{count}\n")
fout.close()
