"""
ID: mck15821
LANG: PYTHON3
PROG: Triangles
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=1012
fin = open('breedflip.in', 'r')
fout = open("breedflip.out", "w")
N = int(fin.readline().strip())
A = fin.readline().strip()
B = fin.readline().strip()
status = []
for i in range(N):
    status.append(A[i] == B[i])

count = 0
i, j = 0, N - 1
# continue to flip the substring between i and j, and count the times of flips
while i <= j:
    while i <= j and status[i] is True:
        i += 1
    while i <= j and status[j] is True:
        j -= 1
    # all True between i and j
    if i > j:
        break
    for k in range(i, j + 1):
        status[k] = not status[k]
    count += 1

fout.write(f"{count}\n")
fout.close()
