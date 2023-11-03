"""
ID: mck15821
LANG: PYTHON3
PROG: Moo Operations
"""
N = int(input())
for _ in range(N):
    s = input().strip()
    if "MOO" in s:
        print(len(s) - 3)
    elif "MOM" in s or "OOO" in s:
        print(len(s) - 3 + 1)
    elif "OOM" in s:
        print(len(s) - 3 + 2)
    else:
        print(-1)
