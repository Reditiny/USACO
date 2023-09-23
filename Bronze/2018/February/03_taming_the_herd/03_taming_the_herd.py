"""
ID: mck15821
LANG: PYTHON3
PROG: taming
"""
fin = open('taming.in', 'r')
fout = open("taming.out", "w")
N = int(fin.readline().strip())
logs = list(map(int, fin.readline().strip().split()))
actual = [-1] * N
actual[0] = 1

i = N - 1
while i > 0:
    if logs[i] > 0:
        actual[i] = 0
        actual[i - logs[i]] = 1
        for j in range(i - 1, i - logs[i], -1):
            # handle -1
            if logs[j] != -1 and logs[j] != logs[i] - (i - j):
                fout.write("-1")
                fout.close()
            actual[j] = 0
        i = i - logs[i]
    # 0 means breakout happens that day
    elif logs[i] == 0:
        actual[i] = 1
    i -= 1

min_count = 0
max_count = 0
for count in actual:
    if count == -1:
        max_count += 1
    elif count == 1:
        max_count += 1
        min_count += 1

fout.write(f"{min_count} {max_count}")
fout.close()
