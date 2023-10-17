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
cows = []
for _ in range(N):
    cows.append(tuple(map(int, fin.readline().strip().split())))


def get_distance_square(id1, id2):
    return (cows[id1][0] - cows[id2][0]) ** 2 + (cows[id1][1] - cows[id2][1]) ** 2


dist = {i: float("inf") for i in range(N)}
dist[0] = 0
result = 0
for _ in range(N):
    min_d_key = min(dist, key=dist.get)  # find the key of minimum distance
    result = max(result, dist[min_d_key])
    del dist[min_d_key]
    for i in dist:
        dist[i] = min(dist[i], get_distance_square(min_d_key, i))

fout.write(f"{result}")
fout.close()
