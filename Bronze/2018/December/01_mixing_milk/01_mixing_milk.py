"""
ID: mck15821
LANG: PYTHON3
PROG: mixmilk
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=855
fin = open('mixmilk.in', 'r')
fout = open("mixmilk.out", "w")

capacities = []
amounts = []
for i in range(3):
    capacity, amount = map(int, fin.readline().strip().split())
    capacities.append(capacity)
    amounts.append(amount)

for i in range(100):
    from_bucket = i % 3
    to_bucket = (i + 1) % 3
    pour = min(amounts[from_bucket], capacities[to_bucket] - amounts[to_bucket])
    amounts[from_bucket] -= pour
    amounts[to_bucket] += pour

for amount in amounts:
    fout.write(f"{amount}\n")
fout.close()
