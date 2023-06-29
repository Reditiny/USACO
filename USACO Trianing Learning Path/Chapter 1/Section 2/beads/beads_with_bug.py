"""
ID: mck15821
LANG: PYTHON3
PROG: beads
"""
fin = open("beads.in", "r")
fout = open("beads.out", "w")
N = int(fin.readline())
beads = fin.readline()
beads = beads.strip() + beads.strip()
max_sum = 0

for s in [beads, beads[::-1]]:
    left_to_right = []
    index = 0
    while s[index] == "w":
        index += 1
    while index < len(s):
        color = s[index]
        index_end = index + 1
        while index_end < len(s) and (s[index_end] == color or s[index_end] == "w"):
            index_end += 1
        if (index_end - index) == len(s):
            left_to_right.append(len(s) // 2)
            break
        left_to_right.append(index_end - index)
        index = index_end
    print(left_to_right)
    max_sum = max(max_sum, left_to_right[0])

    for i in range(0, len(left_to_right) - 1):
        max_sum = max(max_sum, left_to_right[i] + left_to_right[i + 1])

fout.write(str(max_sum) + "\n")
fout.close()
