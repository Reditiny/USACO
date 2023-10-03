"""
ID: mck15821
LANG: PYTHON3
PROG: family
"""
from collections import Counter
# http://www.usaco.org/index.php?page=viewproblem2&cpid=833
fin = open('family.in', 'r')
fout = open("family.out", "w")

N, name_a, name_b = fin.readline().strip().split()
N = int(N)

class TreeNode:
    def __init__(self, name):
        self.name = name
        self.children = []

visited = set()
for _ in range(N):
    name_i, name_j = fin.readline().strip().split()
    if name_i not in visited:
        

fout.close()
