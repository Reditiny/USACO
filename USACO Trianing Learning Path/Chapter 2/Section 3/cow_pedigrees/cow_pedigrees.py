"""
ID: mck15821
LANG: PYTHON3
PROG: nocows
"""
fin = open('nocows.in', 'r')
fout = open("nocows.out", "w")


N, K = map(int, fin.readline().strip().split())  # N: nodes, K: depth
MOD = 9901

table = []  # table[i][j] means number of trees with depth i and nodes of j
small_trees = []  # small_trees[i][j] means number of trees with depth <= i and nodes of j
for i in range(K + 1):
    table.append([0 for _ in range(N + 1)])
    small_trees.append([0 for _ in range(N + 1)])

table[1][1] = 1
for i in range(2, K + 1):  # iterate on depth
    for j in range(1, N + 1, 2):  # total nodes must be odd
        for k in range(1, j // 2 + 1, 2):  # k is nodes in left subtree
            if k != j - 1 - k:  # left k right j - k and left j - k right k are the same thing
                c = 2
            else:  # when left and right has the same amount of nodes
                c = 1

            # 1. left subtree smaller than depth i-1, right is i-1, plus root is i
            # 2. right subtree smaller than depth i-1, left is i-1, plus root is i
            # 3. left subtree and right subtree both depth i-1, plus root is i
            table[i][j] += c * (
                    small_trees[i - 2][k] * table[i - 1][j - 1 - k] \
                           + table[i - 1][k] * small_trees[i - 2][j - 1 - k] \
                           + table[i - 1][k] * table[i - 1][j - 1 - k])
            table[i][j] %= MOD
    for k in range(N + 1):
        small_trees[i - 1][k] += (table[i - 1][k] + small_trees[i - 2][k])
        small_trees[i - 1][k] %= MOD

fout.write(f"{table[K][N]}\n")
fout.close()
