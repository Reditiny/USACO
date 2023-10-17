"""
ID: mck15821
LANG: PYTHON3
PROG: family
"""
# Time complexity: worst case O(n**3), O(n) for iterate node_a ancestor, O(n) for iterate node_b ancestor
# O(n) for find mother
# http://www.usaco.org/index.php?page=viewproblem2&cpid=833
fin = open('family.in', 'r')
fout = open("family.out", "w")

N, name_a, name_b = fin.readline().strip().split()
N = int(N)

relations = [fin.readline().strip().split() for _ in range(N)]


def find_mother(cow):
    for r in relations:
        if r[1] == cow:
            return r[0]
    return None


# return distance between source and target
def get_ancestor_distance(source_cow, target_cow):
    distance = 0
    node = source_cow
    while node is not None:
        if node == target_cow:
            return distance
        node = find_mother(node)
        distance += 1
    return -1


node = name_a
da = 0
while node is not None:
    if get_ancestor_distance(name_b, node) != -1:
        break
    node = find_mother(node)
    da += 1

# cannot find a common ancestor between a and b
if node is None:
    fout.write("NOT RELATED")
    exit(0)

db = get_ancestor_distance(name_b, node)
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
