"""
ID: mck15821
LANG: PYTHON3
PROG: Moo Language
"""
# http://usaco.org/index.php?page=viewproblem2&cpid=1323
N = int(input())
S = input()

result = 0
i = 0
excitement = set()
excitement.add(0)
while i < N - 1:
    print(S[:i + 2])
    if S[i:i + 2] == "BB" or S[i:i + 2] == "EE":
        excitement = set([e + 1 for e in excitement])
        i += 1
    elif S[i:i + 2] == "BE" or S[i:i + 2] == "EB":
        i += 1
    else:
        for e in excitement.copy():
            excitement.add(e + 1)
        i += 1
    print(excitement)

print(len(excitement))
for e in sorted(list(excitement)):
    print(e)




