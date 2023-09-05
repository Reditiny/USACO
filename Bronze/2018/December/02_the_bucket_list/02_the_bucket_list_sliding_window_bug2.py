"""
ID: mck15821
LANG: PYTHON3
PROG: blist
"""
# Trying to use
# http://www.usaco.org/index.php?page=viewproblem2&cpid=856
fin = open('blist.in', 'r')
fout = open("blist.out", "w")

N = int(fin.readline().strip())
start = []
end = []
for _ in range(N):
    s, e, buckets = list(map(int, fin.readline().strip().split()))
    start.append((s, buckets))
    end.append(e)

# sort based on end date
start.sort(key=lambda x: x[0])
end.sort()

# i as pointer to the current end
end_index = 0
bucket_count = 0

print(start)
print(end)

for s in start:
    if s[0] < end[end_index]:
        bucket_count += s[1]
    else:
        end_index += 1

fout.write(f"{bucket_count}\n")
fout.close()
