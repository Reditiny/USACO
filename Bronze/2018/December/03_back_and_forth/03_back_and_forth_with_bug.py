"""
ID: mck15821
LANG: PYTHON3
PROG: backforth
"""
from collections import Counter

# http://www.usaco.org/index.php?page=viewproblem2&cpid=857
fin = open('backforth_bronze_dec18/4.in', 'r')
fout = open("backforth.out", "w")

barn1 = dict(Counter(list(map(int, fin.readline().strip().split()))))
barn2 = dict(Counter(list(map(int, fin.readline().strip().split()))))

result = set()


def dfs(count, balance, b1, b2):
    if count == 4:
        result.add(balance)
        return
    # take from barn1 to barn 2
    if count % 2 == 0:
        for k, v in b1.items():
            if v > 0:
                b1[k] -= 1
                new_b2 = barn2.copy()  # not change barn2 while still reading it
                if k not in new_b2:
                    new_b2[k] = 0
                new_b2[k] += 1
                dfs(count + 1, balance - k, b1.copy(), new_b2)
                b1[k] += 1
    else:
        for k, v in b2.items():
            if v > 0:
                b2[k] -= 1
                new_b1 = b1.copy()
                if k not in new_b1:
                    new_b1[k] = 0
                new_b1[k] += 1
                dfs(count + 1, balance + k, new_b1, b2.copy())
                b2[k] += 1


dfs(0, 0, barn1, barn2)
print(result)

fout.write(f"{len(result)}\n")
fout.close()
