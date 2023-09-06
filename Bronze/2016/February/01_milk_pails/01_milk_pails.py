"""
ID: mck15821
LANG: PYTHON3
PROG: pails
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=615
fin = open('pails.in', 'r')
fout = open("pails.out", "w")

X, Y, M = map(int, fin.readline().strip().split())

# max of i * X + j * Y
max_amount_X = M // X
result = 0
for i in range(max_amount_X):
    x_amount = (i + 1) * X
    remaining = M - x_amount
    y_amount = remaining // Y * Y
    result = max(result, x_amount + y_amount)

fout.write(f"{result}\n")
fout.close()
