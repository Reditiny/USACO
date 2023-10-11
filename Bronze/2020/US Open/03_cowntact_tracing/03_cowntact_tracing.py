"""
ID: mck15821
LANG: PYTHON3
PROG: tracing
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=1037
fin = open('tracing.in', 'r')
fout = open("tracing.out", "w")
N, T = map(int, fin.readline().strip().split())

health_state = [int(c) for c in fin.readline().strip()]  # to compare with the final cow health state

records = []
for _ in range(T):
    t, x, y = map(int, fin.readline().strip().split())
    records.append([t, x - 1, y - 1])  # 0-index

records.sort()
min_k = float("inf")
max_k = -float("inf")
possible_patient_0 = 0


def simulate(patient_zero):
    min_k = float("inf")
    max_k = -float("inf")
    # try every possible k, [0, 250]
    for k in range(251):
        cur_infected = [False] * N
        cur_infected[patient_zero] = True
        time = [0] * N
        for r in records:
            cow1, cow2 = r[1], r[2]
            if cur_infected[cow1]:
                time[cow1] += 1
            if cur_infected[cow2]:
                time[cow2] += 1
            if cur_infected[cow1] and not cur_infected[cow2] and time[cow1] <= k:
                cur_infected[cow2] = True
            if cur_infected[cow2] and not cur_infected[cow1] and time[cow2] <= k:
                cur_infected[cow1] = True
        if cur_infected == health_state:
            min_k = min(min_k, k)
            max_k = max(max_k, k)
    return min_k, max_k


for i in range(len(health_state)):
    if health_state[i] == 1:
        min_k_result, max_k_result = simulate(i)
        # at least 1 k works
        if max_k_result != -float("inf"):
            min_k = min(min_k, min_k_result)
            max_k = max(max_k, max_k_result)
            possible_patient_0 += 1

if max_k == 250:
    max_k = "Infinity"
fout.write(f"{possible_patient_0} {min_k} {max_k}")
fout.close()
