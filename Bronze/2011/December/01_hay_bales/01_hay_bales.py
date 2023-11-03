"""
ID: mck15821
LANG: PYTHON3
PROG: haybales
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=94
fin = open('haybales.in', 'r')
fout = open("haybales.out", "w")

N = int(fin.readline().strip())
piles = []
for _ in range(N):
    piles.append(int(fin.readline().strip()))

average = sum(piles) // N
moves = 0
for pile in piles:
    moves += abs(pile - average)
fout.write(f"{moves // 2}")
fout.close()
