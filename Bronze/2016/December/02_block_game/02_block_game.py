"""
ID: mck15821
LANG: PYTHON3
PROG: blocks
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=664
fin = open('blocks.in', 'r')
fout = open("blocks.out", "w")

N = int(fin.readline().strip())
words = []
for i in range(N):
    words.append(list(fin.readline().strip().split()))

result = [0] * 26
for i in range(N):
    for letter in range(26):
        result[letter] += max(words[i][0].count(chr(letter + 97)), words[i][1].count(chr(letter + 97)))

for i in range(26):
    fout.write(f"{result[i]}\n")
fout.close()
