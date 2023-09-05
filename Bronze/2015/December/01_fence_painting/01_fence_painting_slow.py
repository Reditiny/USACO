"""
ID: mck15821
LANG: PYTHON3
PROG: paint
"""
fin = open('paint.in', 'r')
fout = open("paint.out", "w")

a, b = map(int, fin.readline().strip().split())
c, d = map(int, fin.readline().strip().split())

road = [0 for i in range(101)]
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
fout.write(f"{counter}\n")
fout.close()
