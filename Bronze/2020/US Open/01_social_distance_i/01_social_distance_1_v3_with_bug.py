"""
ID: mck15821
LANG: PYTHON3
PROG: socdist1
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=1035
fin = open('socdist1.in', 'r')
fout = open("socdist1.out", "w")
N = int(fin.readline().strip())
pos = [c for c in fin.readline().strip()]


# def get_smallest_gap(p_str):
#     smallest_gap = 10**5
#     if p_str[0] == "0":
#         i = -1
#     else:
#         i = 0
#     while i < N - 1:
#         next_i = p_str.find("1", i + 1)
#         if next_i == -1:
#             next_i = N
#         gap = next_i - i
#         if i != -1:
#             smallest_gap = min(smallest_gap, gap)
#         i = next_i
#     return smallest_gap
def get_smallest_gap(p_list):
    smallest_gap = 10 ** 5
    index = -1
    for i in range(N):
        if p_list[i] == "1":
            if index != -1 and i - index < smallest_gap:
                smallest_gap = i - index
            index = i
    return smallest_gap


# def get_biggest_gap(p_str):
#     biggest_gap = 0
#     gap_start = -1
#     if p_str[0] == "0":
#         i = -1
#     else:
#         i = 0
#     while i < N - 1:
#         next_i = p_str.find("1", i + 1)
#         if next_i == -1:
#             next_i = N
#         gap = next_i - i
#         if gap > biggest_gap and i != -1:
#             biggest_gap = gap
#             gap_start = i
#         i = next_i
#     return biggest_gap, gap_start
def get_biggest_gap(p_list):
    biggest_gap = 0
    gap_start = -1
    index = -1
    for i in range(N):
        if p_list[i] == "1":
            if index != -1 and i - index > biggest_gap:
                biggest_gap = i - index
                gap_start = index
            index = i
    return biggest_gap, gap_start


answer = 0
biggest_gap, gap_start = get_biggest_gap(pos)

# Scenario 1: Find biggest_gap and place 2 in the 1/3 and 2/3 places
if biggest_gap >= 3:
    new_pos = pos.copy()
    new_pos[gap_start + biggest_gap // 3] = "1"
    new_pos[gap_start + biggest_gap // 3 * 2] = "1"
    answer = max(answer, get_smallest_gap(new_pos))
print(answer)
# Scenario 2: Place 1 on both left and right
if pos[0] == "0" and pos[-1] == "0":
    new_pos = pos.copy()
    new_pos[0] = "1"
    new_pos[-1] = "1"
    answer = max(answer, get_smallest_gap(new_pos))
print(answer)
# Scenario 3: Place 1 on left and 1 in biggest gap
if pos[0] == "0" and pos[-1] != "0":
    new_pos = pos.copy()
    new_pos[gap_start + biggest_gap // 2] = "1"
    new_pos[0] = "1"
    answer = max(answer, get_smallest_gap(new_pos))
print(answer)
# Scenario 4: Place 1 on right and 1 in biggest gap
if pos[0] != "0" and pos[-1] == "0":
    new_pos = pos.copy()
    new_pos[gap_start + biggest_gap // 2] = "1"
    new_pos[-1] = "1"
    answer = max(answer, get_smallest_gap(new_pos))
print(answer, "here")
# Scenario 5: Place 1 in biggest gap twice
if biggest_gap >= 2:
    new_pos = pos.copy()
    new_pos[gap_start + biggest_gap // 2] = "1"
    print(gap_start, biggest_gap)
    biggest_gap, gap_start = get_biggest_gap(new_pos)
    print(gap_start, biggest_gap)
    new_pos[gap_start + biggest_gap // 2] = "1"
    answer = max(answer, get_smallest_gap(new_pos))

fout.write(f"{answer}")
fout.close()
