"""
ID: mck15821
LANG: PYTHON3
PROG: badmilk
"""
fin = open('badmilk.in', 'r')
fout = open("badmilk.out", "w")

N, M, D, S = map(int, fin.readline().strip().split())

# build person -> milk -> [time]
person_to_milk_time = [0] * (N + 1)
for p in range(1, N + 1):
    person_to_milk_time[p] = dict()

for _ in range(D):
    p, m, t = list(map(int, fin.readline().strip().split()))
    if m not in person_to_milk_time[p]:
        person_to_milk_time[p][m] = []
    person_to_milk_time[p][m].append(t)

sick_records = []
for _ in range(S):
    sick_records.append(list(map(int, fin.readline().strip().split())))

# iterate milk type, see whether the milk has been drank by all sick person
# if drank by all sick person before getting sick, then it's a candidate, find all impacted person
# if not then cannot be a bad milk

result = 0
for milk in range(1, M + 1):
    can_be_candidate = True
    for record in sick_records:
        p, t = record
        if milk not in person_to_milk_time[p]:
            can_be_candidate = False
            break
        time_list = person_to_milk_time[p][milk]
        if t <= min(time_list):
            can_be_candidate = False
            break

    if not can_be_candidate:
        continue

    count = 0
    for p in range(1, N + 1):
        if milk in person_to_milk_time[p]:
            count += 1
    result = max(result, count)

fout.write(f"{result}")
fout.close()
