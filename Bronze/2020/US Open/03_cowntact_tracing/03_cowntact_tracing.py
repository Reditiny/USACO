"""
ID: mck15821
LANG: PYTHON3
PROG: tracing
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=1037
fin = open('tracing.in', 'r')
fout = open("tracing.out", "w")
N, T = map(int, fin.readline().strip().split())

health_state = fin.readline().strip()
sick_indexes = []
healthy_indexes = []

for i in range(len(health_state)):
    if health_state[i] == "1":
        sick_indexes.append(i)
    else:
        healthy_indexes.append(i)

records = []
shake_hand_history_per_cow = [[] for _ in range(N)]
for _ in range(T):
    t, x, y = map(int, fin.readline().strip().split())
    records.append([t, x - 1, y - 1])  # 0-index
    shake_hand_history_per_cow[x - 1].append([y - 1, t])
    shake_hand_history_per_cow[y - 1].append([x - 1, t])

records.sort()
for record in records:
    t, x, y = record
    # possible to have 2 cows as patient 0
    if x in sick_indexes and y in sick_indexes:
        shake_hand_history_per_cow[x]




fout.close()
