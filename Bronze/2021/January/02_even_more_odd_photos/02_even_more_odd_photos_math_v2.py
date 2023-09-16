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

while odd > even:
    odd -= 2
    even += 1

if odd < even:
    print(odd * 2 + 1)
else:
    print(odd * 2)
