"""
ID: mck15821
LANG: PYTHON3
PROG: Sleeping in Class
"""
# http://usaco.org/index.php?page=viewproblem2&cpid=1203
T = int(input())
for _ in range(T):
    number_of_entries = int(input())
    entries = list(map(int, input().split()))
    count = 0

    # check whether the sum can be divided into n groups, and the array can be partitioned into n groups
    entries_sum = sum(entries)
    for number_of_groups in range(len(entries), 0, -1):
        if entries_sum % number_of_groups != 0:
            continue
        partition_sum = entries_sum // number_of_groups
        can_partition = True
        cum_sum = 0
        for e in entries:
            cum_sum += e
            if cum_sum > partition_sum:
                can_partition = False
                break
            if cum_sum == partition_sum:
                cum_sum = 0
        if can_partition:
            print(len(entries) - number_of_groups)
            break

