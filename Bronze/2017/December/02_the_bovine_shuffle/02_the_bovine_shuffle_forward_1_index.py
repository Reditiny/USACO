"""
ID: mck15821
LANG: PYTHON3
PROG: shuffle
"""
fin = open('shuffle.in', 'r')
fout = open("shuffle.out", "w")

N = int(fin.readline().strip())
shuffle = list(map(int, fin.readline().strip().split()))
shuffle = [0] + shuffle
previous_shuffle_destination = [-1 for i in range(N + 1)]
for cur_pos in range(1, N + 1):
    next_pos = shuffle[cur_pos]
    # i moves to shuffle[i] in shuffles, so looking backward shuffle[i] moves to i
    previous_shuffle_destination[next_pos] = cur_pos

ids = list(fin.readline().strip().split())
ids = [""] + ids  # amend 0 index

for _ in range(3):
    new_ids = [""] * (N + 1)
    for cur_pos in range(1, N + 1):
        next_pos = previous_shuffle_destination[cur_pos]
        new_ids[next_pos] = ids[cur_pos]  # new id at cur_pos will be the prev_pos value
    ids = new_ids.copy()

for i in range(1, N + 1):
    fout.write(new_ids[i] + "\n")
fout.close()
