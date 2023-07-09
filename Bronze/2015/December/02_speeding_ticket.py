"""
ID: mck15821
LANG: PYTHON3
PROG: speeding
"""
fin = open('speeding.in', 'r')
fout = open("speeding.out", "w")

N, M = map(int, fin.readline().strip().split())
n_list = []
m_list = []
for i in range(N):
    n_list.append(map(int, fin.readline().strip().split()))
for i in range(M):
    m_list.append(map(int, fin.readline().strip().split()))

result = 0
n_index, m_index = 0, 0
cumulative_n, cumulative_m = 0, 0
while n_index < N:
    while cumulative_m < n_list[n_index][0]:
        cumulative_m += m_list[m_index][0]
        result = max(result, m_list[m_index][1] - n_list[n_index][1])
        m_index += 1
    n_index += 1

fout.write(f"{result}\n")
fout.close()
