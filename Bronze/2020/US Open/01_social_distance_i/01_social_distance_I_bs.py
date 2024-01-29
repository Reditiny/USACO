def check(d):
    new_cow = 2
    # 单独检查前缀能插入几头牛 前缀只有一边有牛
    first_distance = cow_positions[0]
    if first_distance >= 2 * d:
        new_cow -= 2
    elif first_distance >= d:
        new_cow -= 1

    # 检查中间部分能插入几头牛同时注意本身距离也要满足条件 中间部分两边都有牛
    for i in range(1, len(cow_positions)):
        distance = cow_positions[i] - cow_positions[i - 1]
        if distance >= 3 * d:
            new_cow -= 2
        elif distance >= 2 * d:
            new_cow -= 1
        elif distance < d:
            return False

    # 单独检查后缀能插入几头牛 后缀只有一边有牛
    last_distance = N - 1 - cow_positions[-1]
    if last_distance >= 2 * d:
        new_cow -= 2
    elif last_distance >= d:
        new_cow -= 1

    return new_cow <= 0

fin = open('socdist1.in', 'r')
fout = open("socdist1.out", "w")
N = int(fin.readline().strip())
cow_positions = [i for i, x in enumerate(fin.readline().strip()) if x == '1']

if not cow_positions:
    fout.write(str(N - 1))
    fout.close()
else:
    left, right = 0, N

    # 二分查找最大的 d
    while left < right:
        mid = (left + right + 1) // 2
        if check(mid):
            left = mid
        else:
            right = mid - 1

    fout.write(str(left))
    fout.close()