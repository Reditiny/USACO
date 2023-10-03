"""
ID: mck15821
LANG: PYTHON3
PROG: evolution
"""
fin = open('evolution.in', 'r')
fout = open("evolution.out", "w")
# http://www.usaco.org/index.php?page=viewproblem2&cpid=941

N = int(fin.readline().strip())
cows = set()
degree = dict()

for _ in range(N):
    line = fin.readline().strip().split()
    # has the point 0
    if int(line[0]) == 0:
        degree[0] = 1
    for i in range(1, int(line[0]) + 1):
        if line[i] not in degree:
            degree[line[i]] = 0
        degree[line[i]] += 1
    cows.add(tuple(line[1:]))


def build_degree(cows):
    degree = dict()
    for cow in cows:
        for feature in cow:
            if feature not in degree:
                degree[feature] = 0
            degree[feature] += 1
    return degree


def build_tree(cows, degree):
    # print(cows, degree)
    if len(cows) <= 1:
        return True
    # find the feature with max number
    max_feature = ""
    max_feature_number = max(degree.values())
    for k, v in degree.items():
        if v == max_feature_number:
            max_feature = k
    # build left subtree with the max_feature
    left_cows, right_cows = set(), set()
    for c in cows:
        if max_feature in c:
            new_c = c[:c.index(max_feature)] + c[c.index(max_feature) + 1:]
            left_cows.add(new_c)
        else:
            right_cows.add(c)
    left_degree = build_degree(left_cows)
    right_degree = build_degree(right_cows)
    # a feature should not be in both left_degree and right_degree
    if len(set(left_degree.keys()).intersection(set(right_degree.keys()))) != 0:
        return False
    return build_tree(left_cows, left_degree) and build_tree(right_cows, right_degree)


result = build_tree(cows, degree)

fout.write("yes" if result is True else "no")
fout.close()
