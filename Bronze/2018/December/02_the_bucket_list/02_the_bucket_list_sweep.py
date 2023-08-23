"""
ID: mck15821
LANG: PYTHON3
PROG: blist
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=856
fin = open('blist.in', 'r')
fout = open("blist.out", "w")

MAX_TIME = 1000
change = [0 for _ in range(MAX_TIME + 1)]

N = int(fin.readline().strip())
requests = []
for _ in range(N):
    start, end, amount = list(map(int, fin.readline().strip().split()))
    change[start] += amount
    change[end] -= amount

max_bucket = 0
cur_bucket = 0
for t in range(MAX_TIME + 1):
    cur_bucket += change[t]
    max_bucket = max(max_bucket, cur_bucket)

fout.write(f"{max_bucket}\n")
fout.close()
