"""
ID: mck15821
LANG: PYTHON3
PROG: hoofball
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=808
fin = open('hoofball.in', 'r')
fout = open("hoofball.out", "w")
N = int(fin.readline().strip())

initials = list(map(int, fin.readline().strip().split()))
initials.sort()

diff = []
for i in range(N):
    if i == 0:
        diff.append(initials[i + 1] - initials[i])
    elif i == N - 1:
        diff.append(initials[i] - initials[i - 1])
    else:
        diff.append(min(initials[i + 1] - initials[i], initials[i] - initials[i - 1]))

result = 0
while True:
    # find peak
    peak = max(diff)
    if peak == -1:
        break

    # find pos
    peak_count = diff.count(peak)
    peak_pos = diff.index(peak)
    # can be multiple peak in a row in diff, but need initials to decide the order
    # without this will fail the last case
    if peak_count > 1 and peak_pos != N - 1:
        other_peak_pos = peak_pos + 1
        while other_peak_pos < N and diff[other_peak_pos] == peak:
            if initials[other_peak_pos] - initials[peak_pos] == peak:
                peak_pos += 1
                other_peak_pos += 1
            else:
                break

    result += 1

    # move from peak
    while True:
        # already visited, break the loop
        if diff[peak_pos] == -1:
            break

        if peak_pos == 0:
            next_pos = peak_pos + 1
        elif peak_pos == N - 1:
            next_pos = peak_pos - 1
        else:
            if initials[peak_pos] - initials[peak_pos - 1] <= initials[peak_pos + 1] - initials[peak_pos]:
                next_pos = peak_pos - 1
            else:
                next_pos = peak_pos + 1

        diff[peak_pos] = -1
        peak_pos = next_pos

fout.write(f"{result}")
fout.close()
