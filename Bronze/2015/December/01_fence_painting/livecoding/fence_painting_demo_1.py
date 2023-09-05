"""
ID:ys15821
LANG:PYTHON3
PROG: paint
"""

fin = open("paint.in", "r")
fout = open("paint.out", "w")

# x = fin.readline().strip().split()
# y = fin.readline().strip().split()

a, b = list(map(int, fin.readline().strip().split()))
c, d = list(map(int, fin.readline().strip().split()))

# a list on whether a stop is painted or not; 0 means not, 1 means yes
road = []
for i in range(101):
    road.append(0)

for i in range(a, b + 1):
    road[i] = 1
for i in range(c, d + 1):
    road[i] = 1

counter = 0
for i in range(1, 101):
    if road[i] == 1:
        counter += 1
    elif road[i - 1] == 1:
        counter -= 1

fout.write(str(counter))
fout.close()