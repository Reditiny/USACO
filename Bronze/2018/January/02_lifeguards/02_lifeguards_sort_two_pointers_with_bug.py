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


def calculate_coverage(intervals, skip):
    accumulated_coverage = 0
    last_end = -1
    for i in range(len(intervals)):
        if i == skip:
            continue
        # When not overlap, intervals[i][0] > last_end
        # When overlap, last_end > intervals[i][0]
        # last_end may be bigger than intervals[i][1], so max with 0
        accumulated_coverage += max(0, intervals[i][1] - max(intervals[i][0], last_end + 1))
        last_end = intervals[i][1] - 1  # BUG
    return accumulated_coverage


max_coverage = 0
for i in range(N):
    max_coverage = max(max_coverage, calculate_coverage(lifeguards, i))

fout.write(f"{max_coverage}")
fout.close()
