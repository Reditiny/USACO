"""
ID: mck15821
LANG: PYTHON3
PROG: backforth
"""
from collections import Counter

# http://www.usaco.org/index.php?page=viewproblem2&cpid=857
fin = open('backforth_bronze_dec18/2.in', 'r')
fout = open("backforth.out", "w")

barn1 = dict(Counter(list(map(int, fin.readline().strip().split()))))
barn2 = dict(Counter(list(map(int, fin.readline().strip().split()))))

result = set()

# carry the same bucket back and forth 2 times, nothing changed
result.add(0)

# carry the same bucket back and forth 1 time, and pick 1 from barn1 then 1 from barn2
for k1 in barn1:
    for k2 in barn2:
        result.add(k2 - k1)

for k1_1 in barn1:
    for k1_2 in barn1:
        for k2_1 in barn2:
            # barn 1 only have 1 bucket x, but using twice; if don't get 1 from barn 2 then it's invalid
            if k1_1 == k1_2 and barn1[k1_1] == 1 and k2_1 != k1_1:
                continue
            for k2_2 in barn2:
                # barn 2 only have 1 bucket x, but using twice; if don't get 1 from barn 1 then it's invalid
                if k2_1 == k2_2 and barn2[k2_1] == 1 and k1_1 != k2_1 and k1_2 != k2_1:
                    continue
                result.add(k2_1 + k2_2 - k1_1 - k1_2)

fout.write(f"{len(result)}\n")
fout.close()
