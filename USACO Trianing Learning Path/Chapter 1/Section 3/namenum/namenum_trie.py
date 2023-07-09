"""
ID: mck15821
LANG: PYTHON3
PROG: namenum
"""
fin = open('namenum.in', 'r')
din = open('dict.txt', 'r')
fout = open("namenum.out", "w")

nums = fin.readline().strip()

class TrieNode:
    def __init__(self):
        self.value = None
        self.next = [None for i in range(26)]


def buildTrie(node, word):
    for c in word:
        if node.next[ord(c) - ord("A")] is None:
            node.next[ord(c) - ord("A")] = TrieNode()
        node = node.next[ord(c) - ord("A")]
    node.value = word


def searchTrie(node, index, s, result):
    if index == len(s):
        if node.value is not None:
            result.append(node.value)
        return
    num = int(s[index])
    original_node = node
    for ch in KEYS[num]:
        print(ch)
        node = original_node.next[ord(ch) - ord("A")]
        if node is None:
            continue
        searchTrie(node, index + 1, s, result)


root = TrieNode()

KEYS = ["", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PRS", "TUV", "WXY"]
result = []

for line in din.readlines():
    buildTrie(root, line.strip())

searchTrie(root, 0, nums, result)

if len(result) == 0:
    fout.write("NONE\n")
for name in result:
    fout.write(name + "\n")
fout.close()