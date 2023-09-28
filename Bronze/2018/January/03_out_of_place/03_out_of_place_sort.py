"""
ID: mck15821
LANG: PYTHON3
PROG: outofplace
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=785
fin = open('outofplace.in', 'r')
fout = open("outofplace.out", "w")
N = int(fin.readline().strip())
places = []
for _ in range(N):
    places.append(int(fin.readline().strip()))

sorted_places = sorted(places)
bad_num = 0
for i in range(N):
    if places[i] != sorted_places[i]:
        bad_num += 1

fout.write(f"{bad_num - 1}\n")
fout.close()
