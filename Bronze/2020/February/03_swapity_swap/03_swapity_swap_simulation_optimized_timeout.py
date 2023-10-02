"""
ID: mck15821
LANG: PYTHON3
PROG: swap
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=1013
fin = open('swap.in', 'r')
fout = open("swap.out", "w")
N, K = map(int, fin.readline().strip().split())
A1, A2 = map(int, fin.readline().strip().split())
B1, B2 = map(int, fin.readline().strip().split())

s = [i + 1 for i in range(N)]

if A1 > B2 or A2 < B1:
    K = K % 2

for _ in range(K):
    s = s[:A1 - 1] + s[A1 - 1:A2][::-1] + s[A2:]
    print(s, "A")
    s = s[:B1 - 1] + s[B1 - 1:B2][::-1] + s[B2:]
    print(s, "B")
for c in s:
    fout.write(f"{c}\n")
fout.close()
