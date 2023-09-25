"""
ID: mck15821
LANG: PYTHON3
PROG: teleport
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=807
fin = open('teleport.in', 'r')
fout = open("teleport.out", "w")

a, b, x, y = map(int, fin.readline().strip().split())

a, b = min(a, b), max(a, b)
x, y = min(x, y), max(x, y)

base_distance = abs(a - b)

teleport_distance = abs(a - x) + abs(b - y)

fout.write(f"{min(base_distance, teleport_distance)}")
fout.close()
