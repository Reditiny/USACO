"""
ID: mck15821
LANG: PYTHON3
PROG: wormsort
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=992
fin = open('wormsort.in', 'r')
fout = open("wormsort.out", "w")

# This is equivalent to build a connected graph to all positions

N, M = map(int, fin.readline().strip().split())
orders = fin.readline().strip().split()
# 0 index
for i in range(N):
    orders[i] -= 1
# for i in range(1, N):
#     if orders[i] < orders[i - 1]:
#         break
# else:
#     fout.write("-1")
#     fout.close()
#     exit(0)
sorted_orders = sorted(orders)
if orders == sorted_orders:
    fout.write("-1")
    fout.close()
    exit(0)

is_ordered = [orders[i] == sorted_orders[i] for i in range(N)]

graph = [[] for _ in range(N)]
for _ in range(M):
    a, b, w = map(int, fin.readline().strip().split())
    graph[a - 1].append(tuple(graph[b - 1], w))
    graph[b - 1].append(tuple(graph[a - 1], w))

# wormholes = sorted(wormholes, key=lambda w: w[1])

visited = [False] * N
for i in range(len(is_ordered)):
    if is_ordered[i]:
        continue







fout.close()
