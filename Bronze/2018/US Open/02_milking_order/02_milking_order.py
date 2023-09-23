"""
ID: mck15821
LANG: PYTHON3
PROG: milkorder
"""
from collections import Counter
# http://www.usaco.org/index.php?page=viewproblem2&cpid=832
fin = open('milkorder.in', 'r')
fout = open("milkorder.out", "w")

N, M, K = map(int, fin.readline().strip().split())
orders = list(map(int, fin.readline().strip().split()))
orders = [cow - 1 for cow in orders]  # change to 0-based index
cows = [-1] * N

# Fill cows to requested spots; if found cow 0 early, then direct return
for _ in range(K):
    cow_number, target_pos = map(int, fin.readline().strip().split())
    if cow_number == 1:
        fout.write(f"{target_pos}")
        fout.close()
        exit(0)
    cows[target_pos - 1] = cow_number - 1


orders_index = 0
# for each cow x in order, found whether x have spot in cows
# if so then schedule all cows with higher class than x before it
for orders_index in range(M):

    # be careful with list index in python
    if orders[orders_index] in cows:
        cows_index = cows.index(orders[orders_index])
    else:
        continue

    # found orders[orders_index] in cows, satisfy the orders before this order_index
    for i in range(orders_index - 1, -1, -1):
        # already handled the items before i, break loop
        if orders[i] in cows:
            break
        for j in range(cows_index - 1, -1, -1):
            if cows[j] == -1:
                cows[j] = orders[i]
                break

# cow 0 settled
for i in range(N):
    if cows[i] == 0:
        fout.write(f"{i + 1}")
        fout.close()
        exit(0)

# cow 0 not settled, but has dependency
if 0 in orders and orders.index(0) != 0:
    # found the latest higher class cow than cow 0, and see if they are already in cows;
    # if so then can just find another -1; if not then also need to fill that cow
    index_of_prior_cow_with_spot_in_orders = orders.index(0) - 1

    if orders[index_of_prior_cow_with_spot_in_orders] in cows:
        index_of_prior_cow_with_spot_in_cows = cows.index(orders[index_of_prior_cow_with_spot_in_orders])
    else:
        index_of_prior_cow_with_spot_in_cows = 0
        index_of_prior_cow_with_spot_in_orders = 0

    # iterate through cows
    for i in range(index_of_prior_cow_with_spot_in_cows, N):
        if cows[i] == orders[index_of_prior_cow_with_spot_in_orders]:
            index_of_prior_cow_with_spot_in_orders += 1
            continue
        if cows[i] == -1:
            # meet cow 0
            if orders[index_of_prior_cow_with_spot_in_orders] == 0:
                fout.write(f"{i + 1}")
                fout.close()
                exit(0)
            cows[i] = orders[index_of_prior_cow_with_spot_in_orders]
            index_of_prior_cow_with_spot_in_orders += 1


# cow 0 not settled and no dependency, found the closest -1
fout.write(f"{cows.index(-1) + 1}")
fout.close()
