"""
ID: mck15821
LANG: PYTHON3
PROG: prefix
"""
import sys
sys.setrecursionlimit(10000)

fin = open('prefix.in', 'r')
fout = open("prefix.out", "w")

seq = []
while True:
    seq_line = fin.readline().strip().split()
    if len(seq_line) == 1 and seq_line[0] == '.':
        break
    seq += seq_line

target = ""
while True:
    target_line = fin.readline().strip()
    if target_line == '':
        break
    target += target_line

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


def searchTrie(node, index, target):
    # print(index, target[index], node.value)
    if index == len(target) or node.next[ord(target[index]) - ord("A")] is None:
        return
    node = node.next[ord(target[index]) - ord("A")]
    if node.value is not None:
        best[0] = max(best[0], index + 1)
        searchTrie(root, index + 1, target)
    searchTrie(node, index + 1, target)


root = TrieNode()
best = [0]

for s in seq:
    buildTrie(root, s)

searchTrie(root, 0, target)

fout.write(f"{best[0]}\n")
fout.close()
