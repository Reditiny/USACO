"""
ID: mck15821
LANG: PYTHON3
PROG: photo
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=95
fin = open('photo.in', 'r')
fout = open("photo.out", "w")

N = int(fin.readline().strip())
photos = []
for _ in range(5):
    photos.append([int(fin.readline().strip()) for i in range(N)])

A = []
for i in range(5):
    for j in range(i + 1, 5):
        candidates = []
        for k in range(N):
            if photos[i][k] == photos[j][k]:
                candidates.append(photos[i][k])


