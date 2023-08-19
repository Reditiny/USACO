"""
ID: mck15821
LANG: PYTHON3
PROG: angry
"""
fin = open('angry.in', 'r')
fout = open("angry.out", "w")

N = int(fin.readline().strip())
bales = []
for _ in range(N):
    bales.append(int(fin.readline().strip()))
bales.sort()

total_explode = 0


fout.close()
