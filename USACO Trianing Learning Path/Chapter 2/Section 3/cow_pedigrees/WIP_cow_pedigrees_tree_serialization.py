"""
ID: mck15821
LANG: PYTHON3
PROG: nocows
"""
fin = open('nocows.in', 'r')
fout = open("nocows.out", "w")


N, K = map(int, fin.readline().strip().split())  # N: nodes, K: depth
MOD = 9901

visited = set()


def serialize(root):
    if root is None:
        return ""
    result = ""
    d = [root]
    while len(d) > 0:
        root = d.pop(0)
        if root is None:
            result += "n "
            continue
        result += str(root.val) + " "
        d.append(root.left)
        d.append(root.right)
    return result.strip()


class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None





fout.write(f"{table[K][N]}\n")
fout.close()
