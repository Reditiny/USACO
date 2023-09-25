"""
ID: mck15821
LANG: PYTHON3
PROG: Do you know your ABCs
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=1059
nums = list(map(int, input().split()))
nums.sort()

if nums[2] == nums[-1] - nums[0] - nums[1]:
    print(f"{nums[0]} {nums[1]} {nums[2]}")
else:
    print(f"{nums[0]} {nums[1]} {nums[3]}")
