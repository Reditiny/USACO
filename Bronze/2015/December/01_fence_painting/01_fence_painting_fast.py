"""
ID: mck15821
LANG: PYTHON3
PROG: paint
"""
fin = open('paint.in', 'r')
fout = open("paint.out", "w")

a, b = map(int, fin.readline().strip().split())
c, d = map(int, fin.readline().strip().split())

if b <= c or a >= d:
    fout.write(f"{b - a + d - c}\n")
else:
    fout.write(f"{max(b, d) - min(a, c)}\n")

fout.close()
