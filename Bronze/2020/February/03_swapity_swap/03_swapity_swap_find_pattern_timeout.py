"""
ID: mck15821
LANG: PYTHON3
PROG: swap
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=1013
fin = open('swap.in', 'r')
fout = open("swap.out", "w")
N, K = map(int, fin.readline().strip().split())
A1, A2 = map(int, fin.readline().strip().split())
B1, B2 = map(int, fin.readline().strip().split())

# 1,2,3,4,5,6,7
# 2,5
# 3,7

# 1,5,4,3,2,6,7
# 1,5,7,6,2,3,4

# 1,2,6,7,5,3,4
# 1,2,4,3,5,7,6

# 1,5,3,4,2,7,6
# 1,5,6,7,2,4,3

# 1,2,4,3,5,7,6
# 1,2,6,7,5,3,4

# 1,5,7,6,2,3,4
# 1,5,4,3,2,6,7

# 1,2,3,4,5,6,7
# 1,2,7,6,5,4,3

# 1,5,6,7,2,4,3
# 1,5,3,4,2,7,6

# 1,2,4,3,5,7,6
# 1,2,6,7,5,3,4

s = [i + 1 for i in range(N)]

if A1 > B2 or A2 < B1:
    K = K % 2

# A -> 0, B -> 1
cur = 1
visited = [[s, cur]]

while True:
    s = s[:A1 - 1] + s[A1 - 1:A2][::-1] + s[A2:]
    cur = 1 - cur
    if [s, cur] in visited:
        break
    visited.append([s, cur])

    s = s[:B1 - 1] + s[B1 - 1:B2][::-1] + s[B2:]
    cur = 1 - cur
    if [s, cur] in visited:
        break
    visited.append([s, cur])

# for item in visited:
#     print(item)

index_of_first_occurrence = visited.index([s, cur])
loop_frequency = (len(visited) - index_of_first_occurrence) // 2
end_state = visited[index_of_first_occurrence + (K - index_of_first_occurrence) % loop_frequency * 2][0]
# print(index_of_first_occurrence, loop_frequency, end_state)

for c in end_state:
    fout.write(f"{c}\n")
fout.close()
