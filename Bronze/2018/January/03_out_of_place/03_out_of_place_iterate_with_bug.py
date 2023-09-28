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

if places[0] > places[1]:
    bessie = 0
    direction = 1
elif places[N - 2] > places[N - 1]:
    bessie = N - 1
    direction = -1
else:
    for i in range(1, N):
        if places[i - 1] > places[i]:
            if places[i] < places[i - 2]:
                bessie = i
                direction = -1
            elif places[i - 1] > places[i + 1]:
                bessie = i - 1
                direction = 1
print(bessie)

count = 0
if direction > 0:
    for i in range(bessie + 1, N):
        if places[bessie] < places[i]:
            break
        count += 1
else:
    for i in range(bessie - 1, -1, -1):
        if places[bessie] > places[i]:
            break
        count += 1

if count == 1:
    fout.write(f"{count}")
else:
    fout.write(f"{count - 1}\n")
fout.close()
