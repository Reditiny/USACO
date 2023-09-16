"""
ID: mck15821
LANG: PYTHON3
PROG: Even more odd photos
"""
# http://usaco.org/index.php?page=viewproblem2&cpid=1084

N = int(input())
cows = list(map(int, input().split()))
max_group = [0]


def backtrack_helper(cur_partition, visited, used):
    latest_partition_satisfied = ((len(cur_partition) % 2) ^ (sum(cur_partition[-1]) % 2) == 1)
    backtrack(cur_partition, visited, used + 1, latest_partition_satisfied)


def backtrack(cur_partition, visited, used, latest_partition_satisfied):
    if used == N:
        if latest_partition_satisfied is True:
            max_group[0] = max(max_group[0], len(cur_partition))
        return
    for i in range(N):
        if visited[i] is True:
            continue
        visited[i] = True
        # empty list
        if len(cur_partition) == 0:
            cur_partition.append([cows[i]])
            backtrack_helper(cur_partition, visited, used)
            cur_partition.pop(-1)
            visited[i] = False
            continue

        # Try adding to the latest partition
        cur_partition[-1].append(cows[i])
        backtrack_helper(cur_partition, visited, used)
        cur_partition[-1].pop(-1)

        if latest_partition_satisfied is True:
            # add to new
            cur_partition.append([cows[i]])
            backtrack_helper(cur_partition, visited, used)
            cur_partition.pop(-1)
        visited[i] = False


backtrack([], [False for _ in range(N)], 0, True)
print(max_group[0])
