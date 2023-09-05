"""
ID: mck15821
LANG: PYTHON3
PROG: mowing
"""
fin = open('mowing.in', 'r')
fout = open("mowing.out", "w")

N = int(fin.readline().strip())

visited = dict()
x, y = 0, 0
visited[(x, y)] = 0

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
        if (x, y) in visited:
            result = min(result, counter - visited[(x, y)])
        visited[(x, y)] = counter

if result > 1000:
    fout.write("-1\n")
else:
    fout.write(f"{result}\n")
fout.close()