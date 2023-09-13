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


def flip(start_i, end_i, start_j, end_j):
    for i in range(start_i, end_i + 1):
        for j in range(start_j, end_j + 1):
            matrix[i][j] = 1 - matrix[i][j]


count = 0
# start from the right bottom corner, than scan from right to left
# after finishing the last line, get one line above
for i in range(N - 1, -1, -1):
    for j in range(i, -1, -1):
        # saved repeated flip
        if i != j and matrix[i][j] == 1 and matrix[j][i] == 1:
            flip(j + 1, i, 0, j)  # left bottom part
            flip(0, j, j + 1, i)  # right top part
            count += 2
        elif matrix[i][j] == 1:
            flip(0, i, 0, j)
            count += 1
        elif matrix[j][i] == 1:
            flip(0, j, 0, i)
            count += 1

fout.write(f"{count}\n")
fout.close()
