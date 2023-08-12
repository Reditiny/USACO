"""
ID: mck15821
LANG: PYTHON3
PROG: sort3
"""
fin = open('sort3.in', 'r')
fout = open("sort3.out", "w")

N = int(fin.readline().strip())
have = []
want = []
for i in range(N):
    have.append(int(fin.readline().strip()))
    want.append(have[i])
want.sort()
swap_count = 0
for i in range(N - 1):
    for j in range(i + 1, N):
        if have[i] != want[i] and have[j] != want[j] and have[i] == want[j] and have[j] == want[i]:
            swap_count += 1
            have[i], have[j] = have[j], have[i]
            break

not_matched_count = 0
# remainders are pairs of swaps to correct 3 numbers
for i in range(N):
    if have[i] != want[i]:
        not_matched_count += 1

swap_count += not_matched_count // 3 * 2
fout.write(f"{swap_count}\n")
fout.close()
