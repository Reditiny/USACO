"""
ID: mck15821
LANG: PYTHON3
PROG: namenum
"""
fin = open('namenum.in', 'r')
din = open('dict.txt', 'r')
fout = open("namenum.out", "w")

MAP = {
    "A": 2, "B": 2, "C": 2,
    "D": 3, "E": 3, "F": 3,
    "G": 4, "H": 4, "I": 4,
    "J": 5, "K": 5, "L": 5,
    "M": 6, "N": 6, "O": 6,
    "P": 7, "R": 7, "S": 7,
    "T": 8, "U": 8, "V": 8,
    "W": 9, "X": 9, "Y": 9
}

nums = fin.readline().strip()
result = []

for line in din.readlines():
    line = line.strip()
    if len(line) != len(nums):
        continue
    match = True
    for i in range(len(nums)):
        if line[i] not in MAP or int(nums[i]) != MAP[line[i]]:
            match = False
            break
    if match:
        result.append(line)

if len(result) == 0:
    fout.write("NONE\n")
for name in result:
    fout.write(name + "\n")
fout.close()