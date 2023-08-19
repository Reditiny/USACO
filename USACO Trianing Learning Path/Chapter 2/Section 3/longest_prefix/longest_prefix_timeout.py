"""
ID: mck15821
LANG: PYTHON3
PROG: prefix
"""
fin = open('prefix.in', 'r')
fout = open("prefix.out", "w")

seq = []
while True:
    seq_line = fin.readline().strip().split()
    if len(seq_line) == 1 and seq_line[0] == '.':
        break
    seq += seq_line

target = ""
while True:
    target_line = fin.readline().strip()
    if target_line == '':
        break
    target += target_line

dp = [False for i in range(len(target) + 1)]
dp[0] = True
best = 0

for i in range(len(target)):
    if dp[i]:
        for s in seq:
            if target[i:i + len(s)] == s:
                dp[i + len(s)] = True
                best = max(best, i + len(s))

fout.write(f"{best}\n")
fout.close()
