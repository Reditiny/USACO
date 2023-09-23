"""
ID: mck15821
LANG: PYTHON3
PROG: lostcow
"""
import math
# http://www.usaco.org/index.php?page=viewproblem2&cpid=735
fin = open('lostcow.in', 'r')
fout = open("lostcow.out", "w")

x, y = map(int, fin.readline().strip().split())

# Approach: The math algorithm focus on accumulating the double of difference to x before finding Bessie
# e.g., 2 * 1 + 2 * 2 + 2 * 4 + 2 * 8 + ... + 2 * 2**n + abs(x - y)
# Since everytime at either side of x the travel distance is to the power of 2
# we need to compare the log of abs(y - x) to the travel distance

# shift x and y to one of it as 0
log_of_diff = int(math.log(abs(y - x), 2))

# if x < y, think of cases of x + 1, x + 4, x + 16, etc. which are an even number after log of 2
# when log is 2, reaching at x + 4 will find Bessie, so the 4 should only count once
# if x > y, think of cases of x - 2, x - 8, x - 32, etc. which are an odd number after log of 2
# when log is 3, reaching at x - 8 will find Bessie, so the 8 should only count once
if x < y and log_of_diff % 2 == 0 or x > y and log_of_diff % 2 == 1:
    # Just found Bessie
    if 2 ** log_of_diff == abs(x - y):
        n_of_end_item = log_of_diff - 1
    # Farmer can exceed log of abs(x - y) on the other side
    else:
        n_of_end_item = log_of_diff + 1
else:
    n_of_end_item = log_of_diff

result = 2 * (2 ** (n_of_end_item + 1) - 1) + abs(x - y)
fout.write(f"{result}")
fout.close()
