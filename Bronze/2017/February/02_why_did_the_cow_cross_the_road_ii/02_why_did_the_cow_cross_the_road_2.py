"""
ID: mck15821
LANG: PYTHON3
PROG: circlecross
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=712
fin = open('circlecross.in', 'r')
fout = open("circlecross.out", "w")
s = fin.readline().strip()

stack = []
visited = [0 for _ in range(26)]
count = 0

for i in range(52):
    ch = ord(s[i]) - ord("A")
    if visited[ch] == 0:
        visited[ch] = 1
        stack.append(s[i])
    else:
        start_pos = 0
        while start_pos < len(stack):
            if stack[start_pos] == s[i]:
                break
            start_pos += 1
        count += len(stack) - start_pos - 1
        stack.pop(start_pos)

fout.write(f"{count}\n")
fout.close()
