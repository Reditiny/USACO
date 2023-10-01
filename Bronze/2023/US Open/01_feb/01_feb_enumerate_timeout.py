"""
ID: mck15821
LANG: PYTHON3
PROG: Moo Language
"""
# http://usaco.org/index.php?page=viewproblem2&cpid=1323
N = int(input())
S = input()

excitement = set()
candidates = set()


def count_excitement(s):
    count = 0
    for i in range(N - 1):
        if s[i] == s[i + 1]:
            count += 1
    return count


q = [S]
while len(q) > 0:
    cur = q.pop(0)
    for i in range(N):
        if cur[i] == "F":
            q.append(cur[:i] + "B" + cur[i+1:])
            q.append(cur[:i] + "E" + cur[i + 1:])
            break
    else:
        # print(cur)
        excitement.add(count_excitement(cur))


print(len(excitement))
for e in sorted(list(excitement)):
    print(e)




