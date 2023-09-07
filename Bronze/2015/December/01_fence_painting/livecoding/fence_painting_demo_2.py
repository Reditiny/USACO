"""
ID:ys15821
LANG:PYTHON3
PROG: paint
"""

fin = open("paint.in", "r")
fout = open("paint.out", "w")

a, b = list(map(int, fin.readline().strip().split()))
c, d = list(map(int, fin.readline().strip().split()))

counter = 0
if b <= c or a >= d:
    counter = (b - a) + (d - c)
else:
    counter = max(b, d) - min(a, c)

fout.write(str(counter))
fout.close()