"""
ID: mck15821
LANG: PYTHON3
PROG: beads
"""
#  Time Complexity: O(n)
fin = open('beads.in', 'r')
fout = open("beads.out", "w")
bNum = int(fin.readline())
beads = fin.readline().strip()
beads = beads + beads

# [r, b]
left = []
right = []
for i in range(800):
    left.append([0, 0])
    right.append([0, 0])

for i in range(1, 2 * bNum + 1):
    if beads[i - 1] == "r":
        left[i][0] = left[i - 1][0] + 1
        left[i][1] = 0
    elif beads[i - 1] == "b":
        left[i][1] = left[i - 1][1] + 1
        left[i][0] = 0
    else:
        left[i][0] = left[i - 1][0] + 1
        left[i][1] = left[i - 1][1] + 1

for i in range(2 * bNum - 1, -1, -1):
    if beads[i] == "r":
        right[i][0] = right[i + 1][0] + 1
        right[i][1] = 0
    elif beads[i] == "b":
        right[i][1] = right[i + 1][1] + 1
        right[i][0] = 0
    else:
        right[i][0] = right[i + 1][0] + 1
        right[i][1] = right[i + 1][1] + 1

# print(left, right)
result = 0
for i in range(2 * bNum):
    result = max(result, max(left[i][0], left[i][1]) + max(right[i][0], right[i][1]))
result = min(result, bNum)

fout.write(str(result) + "\n")
fout.close()
