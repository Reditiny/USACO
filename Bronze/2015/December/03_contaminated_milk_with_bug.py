"""
ID: mck15821
LANG: PYTHON3
PROG: badmilk
"""
fin = open('badmilk.in', 'r')
fout = open("badmilk.out", "w")

N, M, D, S = map(int, fin.readline().strip().split())

milk_to_person_time = [{} for i in range(M + 1)]
sick_person_to_time = {}

for i in range(D):
    p, m, t = map(int, fin.readline().strip().split())
    if p not in milk_to_person_time[m] or milk_to_person_time[m][p] >= t:
        milk_to_person_time[m][p] = t

for i in range(S):
    p, t = map(int, fin.readline().strip().split())
    sick_person_to_time[p] = t

print(milk_to_person_time)
print(sick_person_to_time)

potential_sick_pool = set()

for i in range(M + 1):
    if i == 0:
        continue
    for k, v in sick_person_to_time.items():
        # 1. sick person didn't drink this milk
        # 2. sick person drank but got sick earlier than drinking
        if k not in milk_to_person_time[i] or v <= milk_to_person_time[i][k]:
            continue
    # It's a potential bad milk, update potential sick person
    for j in range(N + 1):
        if j in milk_to_person_time[i]:
            potential_sick_pool.add(j)
    print(potential_sick_pool)

# record = []
# for i in range(N):
#     record.append([[], []]) # [time, milk]
#
# # find the contaminated milk, then see who drank the milk
# for i in range(D):
#     p, m, t = map(int, fin.readline().strip().split())
#     record[p - 1][0].append(t)
#     record[p - 1][1].append(m)

# for i in range(M):


# result = 0
# n_index, m_index = 0, 0
# cumulative_n, cumulative_m = 0, 0
# while n_index < N:
#     while cumulative_m < n_list[n_index][0]:
#         cumulative_m += m_list[m_index][0]
#         result = max(result, m_list[m_index][1] - n_list[n_index][1])
#         m_index += 1
#     n_index += 1

fout.write(f"{len(potential_sick_pool)}\n")
fout.close()
