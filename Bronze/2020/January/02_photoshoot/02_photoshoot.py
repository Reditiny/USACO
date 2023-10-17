"""
ID: mck15821
LANG: PYTHON3
PROG: photo
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=988
fin = open('photo.in', 'r')
fout = open("photo.out", "w")
N = int(fin.readline().strip())
b = list(map(int, fin.readline().strip().split()))

for i in range(1, N + 1):
    output = [i]
    last = i
    for j in range(N - 1):
        last = b[j] - last
        if last in output or last <= 0:
            break
        output.append(last)
    else:
        fout.write(" ".join(list(map(str, output))))
        fout.close()
        exit(0)

fout.close()
