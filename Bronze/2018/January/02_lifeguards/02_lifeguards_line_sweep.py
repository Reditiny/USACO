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

time = [0 for i in range(1000)]
covered = 0
for lifeguard in lifeguards:
    # exclude lifeguard[1], as that is only the end timestamp
    for v in range(lifeguard[0], lifeguard[1]):
        if time[v] == 0:
            covered += 1
        time[v] += 1

result = 0
for lifeguard in lifeguards:
    count = covered
    for v in range(lifeguard[0], lifeguard[1]):
        if time[v] == 1:
            count -= 1
    result = max(result, count)
    if result == covered:
        break

fout.write(f"{result}\n")
fout.close()
