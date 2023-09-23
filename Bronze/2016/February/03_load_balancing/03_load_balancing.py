"""
ID: mck15821
LANG: PYTHON3
PROG: balancing
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=617
fin = open('balancing.in', 'r')
fout = open("balancing.out", "w")

N, B = map(int, fin.readline().strip().split())
positions = []
for _ in range(N):
    positions.append(list(map(int, fin.readline().strip().split())))



fout.close()
