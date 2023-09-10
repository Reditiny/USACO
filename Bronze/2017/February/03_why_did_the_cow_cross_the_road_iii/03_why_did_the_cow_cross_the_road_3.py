"""
ID: mck15821
LANG: PYTHON3
PROG: cowqueue
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=713
fin = open('cowqueue.in', 'r')
fout = open("cowqueue.out", "w")

N = int(fin.readline().strip())
slots = []
for _ in range(N):
    slots.append(list(map(int, fin.readline().strip().split())))

slots.sort()
result = 0
for slot in slots:
    if slot[0] >= result:
        result = slot[0] + slot[1]
    else:
        result += slot[1]

fout.write(f"{result}\n")
fout.close()
