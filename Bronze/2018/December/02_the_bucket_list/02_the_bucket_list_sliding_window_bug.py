"""
ID: mck15821
LANG: PYTHON3
PROG: blist
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=856
fin = open('blist.in', 'r')
fout = open("blist.out", "w")

N = int(fin.readline().strip())
requests = []
for _ in range(N):
    requests.append(list(map(int, fin.readline().strip().split())))

# sort based on end date
requests.sort(key=lambda x: x[1])

# i as pointer to the current end
i = 0
bucket_count = 0
max_count = 0

while i < N:
    bucket_count += requests[i][2]
    j = i + 1
    while j < N and requests[j][0] < requests[i][1]:
        bucket_count += requests[j][2]
        j += 1
    max_count = max(max_count, bucket_count)
    bucket_count -= requests[i][2]
    i += 1

fout.write(f"{max_count}\n")
fout.close()
