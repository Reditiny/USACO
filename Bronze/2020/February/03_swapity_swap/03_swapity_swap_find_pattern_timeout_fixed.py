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

s = tuple([i + 1 for i in range(N)])

if A1 > B2 or A2 < B1:
    K = K % 2

# A -> 0, B -> 1
cur = 1
visited = [(s, cur)]
visited_set = set()  # use set to increase speed of finding
visited_set.add((s, cur))  # list cannot be hashed, so turn into tuple

while True:
    s = s[:A1 - 1] + s[A1 - 1:A2][::-1] + s[A2:]
    cur = 1 - cur
    if (s, cur) in visited_set:
        break
    visited.append((s, cur))
    visited_set.add((s, cur))

    s = s[:B1 - 1] + s[B1 - 1:B2][::-1] + s[B2:]
    cur = 1 - cur
    if (s, cur) in visited_set:
        break
    visited.append((s, cur))
    visited_set.add((s, cur))

index_of_first_occurrence = visited.index((s, cur))
loop_frequency = (len(visited) - index_of_first_occurrence) // 2
end_state = visited[index_of_first_occurrence + (K - index_of_first_occurrence) % loop_frequency * 2][0]

for c in end_state:
    fout.write(f"{c}\n")
fout.close()
