"""
ID: mck15821
LANG: PYTHON3
PROG: family
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=833
fin = open('family.in', 'r')
fout = open("family.out", "w")

N, name_a, name_b = fin.readline().strip().split()
N = int(N)


class TreeNode:
    def __init__(self, name):
        self.name = name
        self.children = []
        self.mother = None


graph = dict()
for _ in range(N):
    name_i, name_j = fin.readline().strip().split()
    if name_i not in graph:
        graph[name_i] = TreeNode(name_i)
    if name_j not in graph:
        graph[name_j] = TreeNode(name_j)
    graph[name_i].children.append(graph[name_j])
    graph[name_j].mother = graph[name_i]


# return distance between source and target
def get_ancestor_distance(source_cow, target_cow):
    distance = 0
    node = source_cow
    while node is not None:
        if node == target_cow:
            return distance
        node = node.mother
        distance += 1
    return -1


node = graph[name_a]
da = 0
while node is not None:
    if get_ancestor_distance(graph[name_b], node) != -1:
        break
    node = node.mother
    da += 1

# cannot find a common ancestor between a and b
if node is None:
    fout.write("NOT RELATED")
    exit(0)

db = get_ancestor_distance(graph[name_b], node)
if da > 1 and db > 1:
    fout.write("COUSINS")
    exit(0)
if da == 1 and db == 1:
    fout.write("SIBLINGS")
    exit(0)
# find a node with higher generation
if da > db:
    da, db = db, da
    name_a, name_b = name_b, name_a

prefix = "great-" * (db - 2)
if db > 1 and da == 0:
    prefix += "grand-"
if da == 0:
    prefix += "mother"
else:
    prefix += "aunt"

fout.write(f"{name_a} is the {prefix} of {name_b}")
fout.close()
