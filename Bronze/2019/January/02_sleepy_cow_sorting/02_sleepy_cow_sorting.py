"""
ID: mck15821
LANG: PYTHON3
PROG: sleepy
"""
fin = open('sleepy.in', 'r')
fout = open("sleepy.out", "w")
N = int(fin.readline().strip())
line = list(map(int, fin.readline().strip().split()))

# 1, 2, 4, 3 -> 2, 4, 1, 3 -> 4, 1, 2, 3 -> 1, 2, 3, 4

# 1, 4, 2, 3 -> 4, 1, 2, 3 -> 1, 2, 3, 4

# 1, 3, 4, 2 -> 3, 4, 1, 2 -> 4, 1, 2, 3 -> 1, 2, 3, 4

# 1, 3, 4, 5, 2

# 4, 3, 2, 1 -> 3, 2, 1, 4 -> 2, 1, 3, 4 -> 1, 2, 3, 4

i = N - 1
while i > 0:
    if line[i] > line[i - 1]:
        i -= 1
    break
fout.write(f"{i}")
fout.close()
