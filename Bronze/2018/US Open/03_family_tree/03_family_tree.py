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
        self.mother = TreeNode()
        self.children = []


visited_nodes = dict()
for _ in range(N):
    name_i, name_j = fin.readline().strip().split()
    if name_i not in visited_nodes:
        visited_nodes[name_i] = TreeNode(name_i)
    if name_j not in visited_nodes:
        visited_nodes[name_j] = TreeNode(name_j)
    visited_nodes[name_i].children.append(visited_nodes[name_j])
    visited_nodes[name_j].mother = visited_nodes[name_i]


def dfs(node, visited, )

# if name_a is name_b's child, then start searching from name_a to ancestor


# if name

fout.close()
