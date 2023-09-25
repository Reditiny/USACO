"""
ID: mck15821
LANG: PYTHON3
PROG: word
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=987
fin = open('word.in', 'r')
fout = open("word.out", "w")
N, K = map(int, fin.readline().strip().split())

words = fin.readline().strip().split()

line_total_character = 0
line = []
for word in words:
    if len(word) + line_total_character <= K:
        line_total_character += len(word)
        line.append(word)
    else:
        fout.write(" ".join(line) + "\n")
        line_total_character = len(word)
        line.clear()
        line.append(word)

if len(line) > 0:
    fout.write(" ".join(line))

fout.close()
