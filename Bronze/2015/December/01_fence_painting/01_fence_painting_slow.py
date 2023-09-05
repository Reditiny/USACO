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
print(road)
fout.write(f"{sum(road) - 1}\n")
fout.close()
