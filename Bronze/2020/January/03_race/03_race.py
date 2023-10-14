"""
ID: mck15821
LANG: PYTHON3
PROG: race
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=989
fin = open('race.in', 'r')
fout = open("race.out", "w")
K, N = map(int, fin.readline().strip().split())

# 2 scenarios:
# 1. run with speed less than X
# 2. run over X and get down to X



def get_shortest_time_to_peak(start_speed, peak_speed, target_distance):
    acceleration_distance = (start_speed + peak_speed) * (peak_speed - start_speed + 1) // 2
    if acceleration_distance > target_distance:
        return -1
    t = ((1 + peak_speed) * peak_speed // 2)
    distance -


def solve(distance):
    steps = 0
    t = 0
    total = 0
    while True:
        t += 1
        steps += 1
        total += steps
        if total >= distance:
            return t


def get_running_time(speed, speed_limit, distance):
    t = 0
    total = 0
    while True:
        total += speed
        t += 1
        if speed <= speed_limit:
            speed += 1
        if total >= distance:
            return t


# No more than, so if below X is OK; reached finish line before or when getting to X
if K - 1 <= (1 + X) * X // 2:
    solve(K - 1)
# Cannot get to speed X + 1
elif K - 1 <= (1 + X + 1) * (X + 1) // 2:


for _ in range(N):
    X = int(fin.readline().strip())

    # cannot get to X
    if K - 1 <= (1 + X - 1) * (X - 1) // 2:
        fout.write(str(get_running_time(0, K, K)))
    elif

fout.close()
