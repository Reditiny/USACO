"""
ID: mck15821
LANG: PYTHON3
PROG: cowsignal
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=665
fin = open('cowsignal.in', 'r')
fout = open("cowsignal.out", "w")

M, N, K = map(int, fin.readline().strip().split())
signal = []
for i in range(M):
    signal.append(list(fin.readline().strip()))

for i in range(M):
    for _ in range(K):
        for j in range(N):
            fout.write(signal[i][j] * K)
        fout.write("\n")
fout.close()
