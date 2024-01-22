
fin = open('billboard.in', 'r')
fout = open("billboard.out", "w")
billboards = [[0]*4 for _ in range(2)]
for i in range(2):
    st = list(map(int, fin.readline().split()))
    for j in range(4):
        billboards[i][j] = st[j] + 1000
# 判断点是否在矩形内
def cover_corner(x, y):
    return x >= billboards[1][0] and x <= billboards[1][2] and y >= billboards[1][1] and y <= billboards[1][3]
# 计算矩形面积
def area(board):
    return (board[2] - board[0]) * (board[3] - board[1])
# 计算两个矩形重叠的面积
def cover_area(board1, board2):
    x_overlap = max(0, min(board1[2], board2[2]) - max(board1[0], board2[0]))
    y_overlap = max(0, min(board1[3], board2[3]) - max(board1[1], board2[1]))
    return x_overlap * y_overlap
# 判断覆盖了几个角
cover_corner_count = 0
if cover_corner(billboards[0][0], billboards[0][1]):
    cover_corner_count += 1
if cover_corner(billboards[0][0], billboards[0][3]):
    cover_corner_count += 1
if cover_corner(billboards[0][2], billboards[0][1]):
    cover_corner_count += 1
if cover_corner(billboards[0][2], billboards[0][3]):
    cover_corner_count += 1
# 如果没能盖住两个角就需要用和原面积一样大的布
ans = area(billboards[0])
if cover_corner_count >= 2:
    ans -= cover_area(billboards[0], billboards[1])

fout.write(f"{ans}\n")
fout.close()
