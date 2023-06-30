"""
ID: mck15821
LANG: PYTHON3
PROG: namenum
"""
fin = open('namenum.in', 'r')
din = open('dict.txt', 'r')
fout = open("namenum.out", "w")

KEYS = ["", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PRS", "TUV", "WXY"]
name_set = set()

for line in din.readlines():
    name_set.add(line.strip())

nums = fin.readline().strip()
candidates = []
result = []

def backtrack(index, temp):
    if index == len(nums):
        candidates.append(temp)
        return
    for ch in KEYS[int(nums[index])]:
        backtrack(index + 1, temp + ch)

backtrack(0, "")
for cand in candidates:
    if cand in name_set:
        result.append(cand)

if len(result) == 0:
    fout.write("NONE\n")
for name in result:
    fout.write(name + "\n")
fout.close()