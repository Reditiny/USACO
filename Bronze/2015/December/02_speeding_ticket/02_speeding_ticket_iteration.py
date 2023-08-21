"""
ID: mck15821
LANG: PYTHON3
PROG: speeding
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=568
fin = open('speeding.in', 'r')
fout = open("speeding.out", "w")

N, M = map(int, fin.readline().strip().split())
n_speeds = []
m_speeds = []
for i in range(N):
    length, speed = map(int, fin.readline().strip().split())
    n_speeds += [speed] * length

for i in range(M):
    length, speed = map(int, fin.readline().strip().split())
    m_speeds += [speed] * length

result = 0
for i in range(100):
    result = max(result, m_speeds[i] - n_speeds[i])

fout.write(f"{result}\n")
fout.close()
