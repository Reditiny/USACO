fin = open('reduce.in', 'r')
fout = open("reduce.out", "w")
n = int(fin.readline())
x = []
y = []
min_x, second_min_x, second_min_y, min_y = 40000, 40000, 40000, 40000
second_max_x, max_x, second_max_y, max_y = 0, 0, 0, 0
# 更新最小、次小、最大、次大的x和y坐标
def update_min_max(x, y):
    global min_x, second_min_x, second_max_x, max_x, min_y, second_min_y, second_max_y, max_y
    if x < min_x:
        second_min_x = min_x
        min_x = x
    elif x < second_min_x:
        second_min_x = x

    if x > max_x:
        second_max_x = max_x
        max_x = x
    elif x > second_max_x:
        second_max_x = x

    if y < min_y:
        second_min_y = min_y
        min_y = y
    elif y < second_min_y:
        second_min_y = y

    if y > max_y:
        second_max_y = max_y
        max_y = y
    elif y > second_max_y:
        second_max_y = y

for _ in range(n):
    xi, yi = map(int, fin.readline().split())
    x.append(xi)
    y.append(yi)
    update_min_max(xi, yi)

ans = (max_x - min_x) * (max_y - min_y)

for i in range(n):
    cur_min_x = min_x if x[i] != min_x else second_min_x
    cur_max_x = max_x if x[i] != max_x else second_max_x
    cur_min_y = min_y if y[i] != min_y else second_min_y
    cur_max_y = max_y if y[i] != max_y else second_max_y

    ans = min(ans, (cur_max_x - cur_min_x) * (cur_max_y - cur_min_y))

fout.write(f"{ans}\n")
fout.close()
