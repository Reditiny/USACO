"""
ID: mck15821
LANG: PYTHON3
PROG: cbarn
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=616
fin = open('cbarn.in', 'r')
fout = open("cbarn.out", "w")

N = int(fin.readline().strip())
rooms = []
for i in range(N):
    rooms.append(int(fin.readline().strip()))

result = 10 ** 9
# pick the start point
for i in range(N):
    acc = 0
    for j in range(N):
        acc += rooms[(i + j) % N] * j  # number of doors the cows passed
    result = min(result, acc)

fout.write(f"{result}\n")
fout.close()
