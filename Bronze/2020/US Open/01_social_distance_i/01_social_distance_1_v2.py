"""
ID: mck15821
LANG: PYTHON3
PROG: socdist1
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=1035
fin = open('socdist1.in', 'r')
fout = open("socdist1.out", "w")
N = int(fin.readline().strip())
pos = fin.readline().strip()
print(pos)
if pos.count("1") == 0:
    fout.write(str(N - 2))
    fout.close()
    exit(0)


def get_smallest_gap(p_list):
    if p_list[0] == "0":
        i = -1
    else:
        i = 0
    while i <= N - 1:
        next_i = p_list.find("1", i + 1)
        if next_i == -1:
            next_i = N
        gap =


gaps = []
i = -1
while i <= N - 1:
    next_i = pos.find("1", i + 1)
    if next_i == -1:
        next_i = N
    gap = next_i - i
    gaps.append(gap)
    i = next_i

print(gaps)

biggest_gap = 0
second_biggest_gap = 0
for gap in gaps:
    if gap > biggest_gap:
        second_biggest_gap = biggest_gap
        biggest_gap = gap
    elif gap > second_biggest_gap:
        second_biggest_gap = gap

if gaps[0]
    1010101






for _ in range(2):
    max_gap = 0
    max_gap_start = -1
    if pos[0] == "1":
        i = 0
    else:
        i = -1
    while i < N - 1:
        next_i = pos.find("1", i + 1)
        if next_i == -1:
            next_i = N
        if i < 0:
            gap = next_i
        gap = next_i - i
        if max_gap < gap:
            max_gap = gap
            max_gap_start = i
        i = next_i
    if max_gap
    change_index = max_gap_start + max_gap // 2
    pos = pos[:change_index] + "1" + pos[change_index + 1:]
    print(pos)

min_gap = len(pos)
if pos[0] == "1":
    i = 0
else:
    i = -1
while i < len(pos) - 1:
    next_i = pos.find("1", i + 1)
    if next_i == -1:
        next_i = N
    gap = next_i - i
    if min_gap > gap:
        min_gap = gap
    i = next_i

fout.write(f"{min_gap}")
fout.close()
