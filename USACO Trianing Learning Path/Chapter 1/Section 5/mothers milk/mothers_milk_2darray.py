"""
ID: mck15821
LANG: PYTHON3
PROG: milk3
"""

fin = open('milk3.in', 'r')
fout = open("milk3.out", "w")


def search(b, c):
    if cache[b][c] == 1:
        return
    cache[b][c] = 1
    a = C - b - c  # amount in A
    # c -> b
    if B < b + c:
        search(B, c - (B - b))
    else:
        search(b + c, 0)
    # b -> c
    if C < b + c:
        search(b - (C - c), C)
    else:
        search(0, b + c)
    # c -> a
    if A < a + c:
        search(b, c - (A - a))
    else:
        search(b, 0)
    # a -> c
    if C < a + c:
        search(b, C)
    else:
        search(b, a + c)
    # b -> a
    if A < a + b:
        search(b - (A - a), c)
    else:
        search(0, c)
    # a -> b
    if B < a + b:
        search(B, c)
    else:
        search(a + b, c)


A, B, C = map(int, fin.readline().strip().split())
cache = []

# init cache, maximum of b and c is 20
for j in range(21):
    arr = [0 for _ in range(21)]
    cache.append(arr)
search(0, C)

everTrue = False
for i in range(C + 1):
    # b and c added up to C, meaning a is 0
    if cache[C - i][i] == 1:
        if i != (C - B) and i != 0:
            fout.write(" ")
        fout.write(f"{i}")
        everTrue = True

if not everTrue:
    fout.write("NONE")
fout.write("\n")
fout.close()
