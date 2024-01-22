fin = open('hoofball.in', 'r')
fout = open("hoofball.out", "w")
N = int(fin.readline())
cows = list(map(int, fin.readline().split()))
# 记录每头牛的传球目标 计算每头牛接到不同球的次数
receive_count = [0] * N
next_cow = [0] * N
# 找到 cow 传球的目标
def pass_ball(cow):
    left_cow = -1
    right_cow = -1
    left_distance = 1000
    right_distance = 1000

    for i in range(N):
        if cows[i] < cows[cow] and cows[cow] - cows[i] < left_distance:
            left_cow = i
            left_distance = cows[cow] - cows[i]
        if cows[i] > cows[cow] and cows[i] - cows[cow] < right_distance:
            right_cow = i
            right_distance = cows[i] - cows[cow]

    if left_distance <= right_distance:
        return left_cow
    return right_cow

for i in range(N):
    next_cow[i] = pass_ball(i)
    receive_count[next_cow[i]] += 1

ans = 0
for i in range(N):
    # 如果这头牛不会接到其他牛的球，那么需要给它一颗球
    if receive_count[i] == 0:
        ans += 1
    # 两头牛只会接到彼此的球，那么需要给其中一头牛一颗球
    if i < next_cow[i] and next_cow[next_cow[i]] == i \
            and receive_count[i] == 1 and receive_count[next_cow[i]] == 1:
        ans += 1

fout.write(f"{ans}")
fout.close()
