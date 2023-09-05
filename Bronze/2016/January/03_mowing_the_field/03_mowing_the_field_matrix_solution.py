"""
ID: mck15821
LANG: PYTHON3
PROG: mowing
"""
fin = open('mowing.in', 'r')
fout = open("mowing.out", "w")

N = int(fin.readline().strip())
board = []
# build a board of 2001 * 2001, and start at point (1000, 1000)
for _ in range(2001):
    board.append([-1 for i in range(2001)])

x, y = 1000, 1000
board[x][y] = 0
result = 1001  # max is 100 * 10

counter = 0

for _ in range(N):
    d, length = fin.readline().strip().split()
    length = int(length)
    for k in range(length):
        if d == "N":
            y -= 1
        elif d == "S":
            y += 1
        elif d == "W":
            x -= 1
        elif d == "E":
            x += 1
        counter += 1
        if board[x][y] != -1:
            result = min(result, counter - board[x][y])
        board[x][y] = counter

if result > 1000:
    fout.write("-1\n")
else:
    fout.write(f"{result}\n")
fout.close()