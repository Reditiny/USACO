"""
ID: mck15821
LANG: PYTHON3
PROG: race
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=989
import math
fin = open('race.in', 'r')
fout = open("race.out", "w")
K, N = map(int, fin.readline().strip().split())

# 2 scenarios:
# 1. run with speed less or equal to X
# 2. run over X and get down to X

# Thoughts:
# 1. Can group speed with the same value together; no difference of speed sequence
# 2. The speed drop phase only decrease speed, not keep speed unchanged


def get_running_time(speed_limit, distance):
    t = 0
    total = 0
    speed = 1
    while True:
        t += 1
        total += speed
        if speed < speed_limit:
            speed += 1
        if total >= distance:
            return t


for _ in range(N):
    X = int(fin.readline().strip())
    if (1 + X + 1) * (X + 1) // 2 > K - 1:
        fout.write(f"{get_running_time(X, K)}\n")
    # Accelerate first than drop the speed, need to find a common peak speed
    else:
        top_speed = X + 1
        while True:
            speed_increase_distance = (1 + top_speed - 1) * (top_speed - 1) // 2  # 1 increase to top_speed - 1
            speed_drop_distance = (X + 1 + top_speed) * (top_speed - (X + 1) + 1) // 2  # top_speed drop to X + 1
            if speed_increase_distance + speed_drop_distance > K - 1:
                break
            top_speed += 1

        top_speed -= 1
        speed_increase_distance = (1 + top_speed - 1) * (top_speed - 1) // 2
        speed_drop_distance = (X + 1 + top_speed) * (top_speed - (X + 1) + 1) // 2
        # print(K, X, top_speed, speed_increase_distance, speed_drop_distance, K - speed_increase_distance - speed_drop_distance)

        total_time = 0
        speed_increase_time = top_speed - 1
        speed_drop_time = top_speed - (X + 1) + 1
        total_time += speed_increase_time + speed_drop_time

        # the extra distance can be done by speed X
        rest_of_distance = K - speed_increase_distance - speed_drop_distance

        while rest_of_distance >= top_speed:
            rest_of_distance -= top_speed
            total_time += 1
        if rest_of_distance <= X:
            total_time += 1
        else:
            total_time += 2

        # print(speed_increase_time, speed_drop_time, total_time)

        fout.write(f"{total_time}\n")
fout.close()
