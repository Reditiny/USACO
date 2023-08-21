"""
ID: mck15821
LANG: PYTHON3
PROG: speeding
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=568
fin = open('speeding.in', 'r')
fout = open("speeding.out", "w")

N, M = map(int, fin.readline().strip().split())
n_list = []
m_list = []
for i in range(N):
    n_list.append(list(map(int, fin.readline().strip().split())))

for i in range(M):
    m_list.append(list(map(int, fin.readline().strip().split())))

acc_n = 0
acc_m = 0
i, j = 0, 0
result = 0
while i < N:
    acc_n += n_list[i][0]
    if acc_n < acc_m:
        result = max(result, m_list[j - 1][1] - n_list[i][1])
    while acc_m < acc_n:
        acc_m += m_list[j][0]
        result = max(result, m_list[j][1] - n_list[i][1])
        j += 1
    i += 1

fout.write(f"{result}\n")
fout.close()
