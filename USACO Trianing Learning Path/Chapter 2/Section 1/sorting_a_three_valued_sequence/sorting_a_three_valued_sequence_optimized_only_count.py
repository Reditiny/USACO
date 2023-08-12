"""
ID: mck15821
LANG: PYTHON3
PROG: sort3
"""
fin = open('sort3.in', 'r')
fout = open("sort3.out", "w")

N = int(fin.readline().strip())
numbers = []
count = [0, 0, 0]

for i in range(N):
    num = int(fin.readline().strip())
    numbers.append(num)
    count[num - 1] += 1

start = [0, count[0], count[0] + count[1]] # in the final ordered list, the start location of 1, 2 and 3
from_and_to = [[0, 0], [0, 0], [0, 0]]

# for each 1, 2 and 3...
for i in range(3):
    # for each occurence of i...
    for j in range(count[i]):
        total = numbers[start[i] + j] # the nth number should falls in i bucket
        from_and_to[total - 1][i] += 1

swap_count = 0

# swap between 2 buckets
for i in range(3):
    from_bucket = i
    to_bucket = (i + 1) % 3
    diff = min(from_and_to[from_bucket][to_bucket], from_and_to[to_bucket][from_bucket])
    swap_count += diff
    from_and_to[from_bucket][to_bucket] -= diff
    from_and_to[to_bucket][from_bucket] -= diff

# cycles
swap_count += from_and_to[0][1] + from_and_to[0][2]

not_matched_count = 0
# remainders are pairs of swaps to correct 3 numbers
for i in range(N):
    if have[i] != want[i]:
        not_matched_count += 1

swap_count += not_matched_count // 3 * 2
fout.write(f"{swap_count}\n")
fout.close()
