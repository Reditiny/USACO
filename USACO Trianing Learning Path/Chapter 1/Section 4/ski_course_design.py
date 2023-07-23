"""
ID: mck15821
LANG: PYTHON3
PROG: skidesign
"""

fin = open('skidesign.in', 'r')
fout = open("skidesign.out", "w")
number_of_hills = int(fin.readline().strip())
hills = []
for i in range(number_of_hills):
    hills.append(int(fin.readline()))

result = float("inf")
for i in range(84):
    cost = 0
    diff = 0
    for j in range(number_of_hills):
        if i > hills[j]:
            diff = i - hills[j]
        elif i < hills[j] - 17:
            diff = hills[j] - 17 - i
        else:
            diff = 0
        cost += diff * diff
    result = min(result, cost)

fout.write(f"{result}\n")
fout.close()

