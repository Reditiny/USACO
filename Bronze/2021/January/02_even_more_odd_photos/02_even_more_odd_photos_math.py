"""
ID: mck15821
LANG: PYTHON3
PROG: Even more odd photos
"""
# http://usaco.org/index.php?page=viewproblem2&cpid=1084

N = int(input())
cows = list(map(int, input().split()))

odd, even = 0, 0

for cow in cows:
    if cow % 2 == 0:
        even += 1
    else:
        odd += 1

# odd number can add up to even, but even number cannot add up to odd
if even == odd:
    print(odd * 2)
# 1 extra even
elif even > odd:
    print(odd * 2 + 1)
else:
    diff = odd - even
    total = even * 2
    total += diff // 3 * 2
    diff %= 3

    # 1 extra odd left, need to merge with the last odd and become even, also merge the last even
    if diff == 1:
        total -= 1
    # 2 extra odd left, combine them into 1 new even
    elif diff == 2:
        total += 1
    print(total)
