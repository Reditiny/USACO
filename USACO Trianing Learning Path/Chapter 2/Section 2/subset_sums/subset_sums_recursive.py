"""
ID: mck15821
LANG: PYTHON3
PROG: subset
"""
fin = open('subset.in', 'r')
fout = open("subset.out", "w")

N = int(fin.readline().strip())

sum_N = N * (N + 1) // 2
if sum_N % 2 == 1:
    fout.write("0\n")
    fout.close()
    exit(0)
target = sum_N // 2

results = []
for i in range(target + 1):
    results.append([-1 for j in range(N + 1)])


def find(n, k):
    if n < 0 or k < 0:
        return 0
    if results[n][k] != -1:
        return results[n][k]
    if n == 0 and k == 0:
        return 1
    results[n][k] = find(n - k, k - 1) + find(n, k - 1)
    return results[n][k]

fout.write(f"{find(target, N) // 2}\n")
fout.close()
