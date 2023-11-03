fin = open("diamond.in", "r")
fout = open("diamond.out", "w")

maxcase = []
diamonds =[]

n, k = map(int, fin.readline().strip().split())
for diamond in range(n):
    diamonds.append(int(fin.readline()))

print(diamonds)

for diamond1 in diamonds:
    count = 0
    for diamond2 in diamonds:
        if diamond1 + k >= diamond2 >= diamond1:
            count += 1
    maxcase.append(count)

fout.write(str(max(maxcase)))


# implement sort list then one path find all