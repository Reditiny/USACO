"""
ID: mck15821
LANG: PYTHON3
PROG: moocast
"""
# Time complexity: O(N^2 * log(N^2) + log(N^2) * N^2) = O(N^2 * log(N^2))
# http://www.usaco.org/index.php?page=viewproblem2&cpid=669
fin = open('moocast.in', 'r')
fout = open("moocast.out", "w")
N = int(fin.readline().strip())
coordinates = []
for _ in range(N):
    coordinates.append(list(map(int, fin.readline().strip().split())))


def get_distance_square(id1, id2):
    return (coordinates[id1][0] - coordinates[id2][0]) ** 2 + (coordinates[id1][1] - coordinates[id2][1]) ** 2


X_candidates = set()
for i in range(N):
    for j in range(i + 1, N):
        d_square = get_distance_square(i, j)
        X_candidates.add(d_square)
X_candidates = sorted(list(X_candidates))


def dfs(visited, cur, X):
    visited[cur] = True
    for i in range(N):
        if not visited[i] and get_distance_square(cur, i) <= X:
            dfs(visited, i, X)


def can_traverse_all(X):
    visited = [False for _ in range(N)]
    dfs(visited, 0, X)
    return sum(visited) == N


# binary search to find X that can traverse all nodes
left, right = 0, len(X_candidates) - 1
while left < right:
    mid = (left + right) // 2
    traverse_result = can_traverse_all(X_candidates[mid])
    if traverse_result:
        right = mid
    else:
        left = mid + 1
if can_traverse_all(X_candidates[left]):
    fout.write(f"{X_candidates[left]}")
else:
    fout.write(f"{X_candidates[left + 1]}")

fout.close()
