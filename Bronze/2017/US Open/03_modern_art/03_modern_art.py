"""
ID: mck15821
LANG: PYTHON3
PROG: art
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=737
fin = open('art.in', 'r')
fout = open("art.out", "w")

N = int(fin.readline().strip())
arts = []
colors = set()
for i in range(N):
    line = fin.readline().strip()
    num_line = []
    for c in line:
        num_line.append(int(c))
        if int(c) != 0:
            colors.add(int(c))
    arts.append(num_line)

top_left_bottom_right_dict = dict()

# to have maximum of first painted color, we want to have has little overlapping as possible, and each rect as small as possible
# e.g., if we have
# 1 2 3
# 4 5 6
# 7 8 9
# then we have 9 possibilities
# if two rect overlapped, then further breaks into 3 conditions:
# 1. overlapping area has both A and B, which cannot happen
# 2. one of A or B shows, then whatever shows got painted later
# 3. neither of A or B shows, then they all got covered, it doesn't matter which one shows first
# In this question, just verify each color i and see whether i comes later than any color; if no then add 1 to result
def get_rect_top_left_and_bottom_right(color):
    if color in top_left_bottom_right_dict:
        return top_left_bottom_right_dict[color]
    top_left_i = N - 1
    top_left_j = N - 1
    bottom_right_i = 0
    bottom_right_j = 0
    for i in range(N):
        for j in range(N):
            if arts[i][j] == color:
                top_left_i = min(top_left_i, i)
                top_left_j = min(top_left_j, j)
                bottom_right_i = max(bottom_right_i, i)
                bottom_right_j = max(bottom_right_j, j)

    top_left_bottom_right_dict[color] = [[top_left_i, top_left_j], [bottom_right_i, bottom_right_j]]
    return top_left_bottom_right_dict[color]


def is_a_cover_b(a, b):
    top_left, bottom_right = get_rect_top_left_and_bottom_right(b)
    for r in range(top_left[0], bottom_right[0] + 1):
        for c in range(top_left[1], bottom_right[1] + 1):
            if a == arts[r][c]:
                return True
    return False


# build top left and bottom right coordinates for each rect of the color
for color in colors:
    get_rect_top_left_and_bottom_right(color)

result = 0
# pick 1 color i, and compare with all other rect, and if no color drew prior to i, increase 1 candidate
for i in colors:
    is_valid = True
    for j in colors:
        if i == j or i not in top_left_bottom_right_dict or j not in top_left_bottom_right_dict:
            continue
        if is_a_cover_b(i, j):
            is_valid = False
            break
    else:
        result += 1

fout.write(f"{result}\n")
fout.close()
