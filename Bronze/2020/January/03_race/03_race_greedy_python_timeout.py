"""
ID: mck15821
LANG: PYTHON3
PROG: race
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=989
fin = open('race.in', 'r')
fout = open("race.out", "w")
K, N = map(int, fin.readline().strip().split())


def get_fastest_time(distance, speed_limit):
    speed_increase_distance = 0
    speed_decrease_distance = 0
    time = 0

    cur_speed = 1
    while True:
        speed_increase_distance += cur_speed
        time += 1
        if speed_increase_distance + speed_decrease_distance >= distance:
            return time

        if cur_speed >= speed_limit:
            speed_decrease_distance += cur_speed
            time += 1
            if speed_increase_distance + speed_decrease_distance >= distance:
                return time
        cur_speed += 1


for _ in range(N):
    X = int(fin.readline().strip())
    fout.write(f"{get_fastest_time(K, X)}\n")
fout.close()
