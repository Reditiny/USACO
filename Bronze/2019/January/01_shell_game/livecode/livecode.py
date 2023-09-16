# http://www.usaco.org/index.php?page=viewproblem2&cpid=891

fin = open("shell.in", "r")
fout = open("shell.out", "w")

# input
N = int(fin.readline().strip())

a_list = []
b_list = []
g_list = []

for i in range(N):
    a, b, g = list(map(int, fin.readline().strip().split()))
    a_list.append(a)
    b_list.append(b)
    g_list.append(g)

print(a_list, b_list, g_list)

# coding logic




# final answer

fout.write(str(ans))
fout.close()

