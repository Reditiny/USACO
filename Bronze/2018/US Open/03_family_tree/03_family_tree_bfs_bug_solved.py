"""
ID: mck15821
LANG: PYTHON3
PROG: family
"""
# Time complexity: best case O(log(N) * 2), worst case O(n * 2)
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

# Check Siblings
if graph[name_a].mother == graph[name_b].mother:
    fout.write("SIBLINGS")
    exit(0)

# Check whether is mother and child
if graph[name_a].mother == graph[name_b]:
    fout.write(f"{name_b} is the mother of {name_a}")
    exit(0)
elif graph[name_b].mother == graph[name_a]:
    fout.write(f"{name_a} is the mother of {name_b}")
    exit(0)


def check_relationships(source_name, target_name):
    node = graph[source_name]
    steps = 0
    ancestor_set = set()
    while node is not None:
        ancestor_set.add(node.name)
        if node == graph[target_name]:
            relationship = "great-" * (steps - 2) + "grand-mother"
            return relationship, ancestor_set
        elif graph[target_name] in node.children:
            relationship = "great-" * (steps - 2) + "aunt"
            return relationship, ancestor_set
        steps += 1
        node = node.mother
    return None, ancestor_set


relationship, ancestor_set_a = check_relationships(name_a, name_b)
if relationship is not None:
    fout.write(f"{name_b} is the {relationship} of {name_a}")
    exit(0)

relationship, ancestor_set_b = check_relationships(name_b, name_a)
if relationship is not None:
    fout.write(f"{name_a} is the {relationship} of {name_b}")
    exit(0)

if len(ancestor_set_a.intersection(ancestor_set_b)) > 0:
    fout.write("COUSINS")
else:
    fout.write("NOT RELATED")
fout.close()
