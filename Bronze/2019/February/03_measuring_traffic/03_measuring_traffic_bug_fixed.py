"""
ID: mck15821
LANG: PYTHON3
PROG: traffic
"""
fin = open('traffic.in', 'r')
fout = open("traffic.out", "w")

N = int(fin.readline().strip())
segments = []
for i in range(N):
    segments.append(list(fin.readline().strip().split()))

left, right = -float("inf"), float("inf")
for k in range(N - 1, -1, -1):
    if segments[k][0] == "none":
        left = max(left, int(segments[k][1]))
        right = min(right, int(segments[k][2]))
    if segments[k][0] == "on":
        left -= int(segments[k][2])
        right -= int(segments[k][1])
        left = max(left, 0)
    elif segments[k][0] == "off":
        left += int(segments[k][1])
        right += int(segments[k][2])
fout.write(f"{left} {right}\n")


left, right = -float("inf"), float("inf")
for j in range(N):
    if segments[j][0] == "none":
        left = max(left, int(segments[j][1]))
        right = min(right, int(segments[j][2]))
    if segments[j][0] == "on":
        left += int(segments[j][1])
        right += int(segments[j][2])
    elif segments[j][0] == "off":
        left -= int(segments[j][2])
        right -= int(segments[j][1])
        left = max(left, 0)
fout.write(f"{left} {right}\n")





fout.close()
