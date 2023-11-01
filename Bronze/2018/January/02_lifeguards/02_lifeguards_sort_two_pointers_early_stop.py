"""
ID: mck15821
LANG: PYTHON3
PROG: lifeguards
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=784
fin = open('lifeguards.in', 'r')
fout = open("lifeguards.out", "w")
N = int(fin.readline().strip())
lifeguards = []
for _ in range(N):
    lifeguards.append(list(map(int, fin.readline().strip().split())))
lifeguards = sorted(lifeguards)

# min_gap is the minimum gap generated with 1 interval removed
min_gap = min(lifeguards[0][1], lifeguards[1][0]) - lifeguards[0][0]
accumulated_coverage = lifeguards[0][1] - lifeguards[0][0]
last_end = lifeguards[0][1]

for i in range(1, len(lifeguards) - 1):
    cur_interval = lifeguards[i]
    gap_last_end = last_end  # need to update through iteration of next interval, so need a new variable
    cur_gap = 0
    for j in range(i + 1, N):
        next_interval = lifeguards[j]
        cur_gap_start = max(gap_last_end, cur_interval[0])
        cur_gap_end = min(cur_interval[1], next_interval[0])
        # gap_last_end can be bigger than cur_interval[1], so start can be bigger than end so check first
        if cur_gap_end > cur_gap_start:
            cur_gap += cur_gap_end - cur_gap_start

        gap_last_end = max(gap_last_end, next_interval[1])
        # the previous interval covered current interval, no need to continue
        if gap_last_end > cur_interval[1]:
            break
    min_gap = min(min_gap, cur_gap)
    # move last_end
    if cur_interval[1] > last_end:
        accumulated_coverage += cur_interval[1] - max(cur_interval[0], last_end)
        last_end = cur_interval[1]

if lifeguards[N - 1][1] > last_end:
    accumulated_coverage += lifeguards[N - 1][1] - max(last_end, lifeguards[N - 1][0])
    min_gap = min(min_gap, lifeguards[N - 1][1] - last_end)
else:
    min_gap = 0

fout.write(f"{accumulated_coverage - min_gap}")
fout.close()
