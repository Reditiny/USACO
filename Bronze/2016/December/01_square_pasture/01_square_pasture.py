"""
ID: mck15821
LANG: PYTHON3
PROG: square
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=663
fin = open('square.in', 'r')
fout = open("square.out", "w")

x1, y1, x2, y2 = map(int, fin.readline().strip().split())
x3, y3, x4, y4 = map(int, fin.readline().strip().split())

fout.write(f"{max(max(x2, x4) - min(x1, x3), max(y2, y4) - min(y1, y3)) ** 2}")
fout.close()
