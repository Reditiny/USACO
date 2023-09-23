"""
ID: mck15821
LANG: PYTHON3
PROG: sleepy
"""
fin = open('sleepy.in', 'r')
fout = open("sleepy.out", "w")
N = int(fin.readline().strip())
line = list(map(int, fin.readline().strip().split()))

i = N - 1
while i > 0:
    if line[i] > line[i - 1]:
        i -= 1
    else:
        break
fout.write(f"{i}")
fout.close()
