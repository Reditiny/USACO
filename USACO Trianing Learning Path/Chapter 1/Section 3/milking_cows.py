"""
ID: mck15821
LANG: PYTHON3
PROG: milk2
"""
#  Time Complexity: O(n^2)
fin = open('milk2.in', 'r')
fout = open("milk2.out", "w")
number_of_farmers = int(fin.readline())
schedules = []
for i in range(number_of_farmers):
    start, end = fin.readline().strip().split()
    schedules.append([int(start), int(end)])

schedules = sorted(schedules, key=lambda x: x[0])
max_start = schedules[0][0]
max_end = schedules[0][1]
max_milk = max_end - max_start
max_no_milk = 0

for interval in schedules:
    max_no_milk = max(interval[0] - max_end, max_no_milk)
    if interval[0] <= max_end:
        max_end = max(max_end, interval[1])
    else:
        max_milk = max(max_end - max_start, max_milk)
        max_start, max_end = interval

fout.write(f"{max_milk} {max_no_milk}\n")
fout.close()

