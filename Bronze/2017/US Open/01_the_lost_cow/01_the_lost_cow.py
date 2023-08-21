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
direction = 1
while True:
    if x <= y <= x + distance * direction or x + distance * direction <= y <= x:
        result += abs(y - x)
        break
    else:
        result += distance * 2  # back and forth
        distance *= 2
        direction *= -1

fout.write(f"{result}\n")
fout.close()
