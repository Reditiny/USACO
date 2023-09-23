"""
ID: mck15821
LANG: PYTHON3
PROG: lostcow
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=735
fin = open('lostcow.in', 'r')
fout = open("lostcow.out", "w")

x, y = map(int, fin.readline().strip().split())

result = 0
distance = 1
while True:
    if x <= y <= x + distance or x + distance <= y <= x:
        result += abs(y - x)
        break
    else:
        result += distance * 2  # back and forth
        distance *= -2

fout.write(f"{result}\n")
fout.close()
