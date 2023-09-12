"""
ID: mck15821
LANG: PYTHON3
PROG: air conditioning ii
"""
N, M = map(int, input().split())
target = [0 for _ in range(101)]  # total 100 stalls
air_conditioning = []

for _ in range(N):
    start, end, value = map(int, input().split())
    for i in range(start, end + 1):
        target[i] = max(target[i], value)

for _ in range(M):
    air_conditioning.append(list(map(int, input().split())))

min_value = [10 * 1000]  # max 10 air conditioning and 1000 cost for each one
machine_visited_set = set()


def check_satisfied(stalls):
    for i in range(len(stalls)):
        if stalls[i] < target[i]:
            return False
    return True


def backtrack(stalls, machine_state, cur):
    if machine_state in machine_visited_set:
        return
    machine_visited_set.add(machine_state)
    # condition satisfied, no need to explore
    if check_satisfied(stalls) is True:
        min_value[0] = min(min_value[0], cur)
        return

    for i in range(M):
        if machine_state[i] == "1":
            continue
        start, end, temperature, cost = air_conditioning[i]
        new_stalls = stalls.copy()
        for j in range(start, end + 1):
            new_stalls[j] += temperature
        backtrack(new_stalls, machine_state[:i] + "1" + machine_state[i+1:], cur + cost)


backtrack([0 for _ in range(101)], "".join(["0" for _ in range(M)]), 0)

print(f"{min_value[0]}")
