"""
ID: mck15821
LANG: PYTHON3
PROG: namenum
"""
fin = open('namenum.in', 'r')
din = open('dict.txt', 'r')
fout = open("namenum.out", "w")

KEYS = ["", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PRS", "TUV", "WXY"]
name_list = []

for line in din.readlines():
    name_list.append(line.strip())

nums = fin.readline().strip()
result = []


def binary_search_algo(start, end, temp, index):
    if index == len(nums):
        if name_list[start] == temp:
            result.append(temp)
        return
    if start > end:
        return
    original_start, original_end = start, end
    for ch in KEYS[int(nums[index])]:
        # narrow down new start and end
        left, right = original_start, original_end
        # find new start
        while left < right:
            mid = (left + right) // 2
            if len(name_list[mid]) <= index:
                left += 1
                continue
            if name_list[mid][index] == ch:
                right = mid
            elif name_list[mid][index] > ch:
                right = mid - 1
            else:
                left = mid + 1
        if len(name_list[left]) <= index or name_list[left][index] != ch:
            continue
        new_start = left
        # find new end
        left, right = original_start, original_end
        while left < right:
            mid = (left + right) // 2
            if len(name_list[mid]) <= index:
                left += 1
                continue
            if name_list[mid + 1][index] == ch:
                left = mid + 1
            elif name_list[mid + 1][index] > ch:
                right = mid
            else:
                left = mid + 1
        new_end = right

        binary_search_algo(new_start, new_end, temp + ch, index + 1)


binary_search_algo(0, len(name_list) - 1, "", 0)

if len(result) == 0:
    fout.write("NONE\n")
for name in result:
    fout.write(name + "\n")
fout.close()
