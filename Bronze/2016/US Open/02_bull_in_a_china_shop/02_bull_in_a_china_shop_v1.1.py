"""
ID: mck15821
LANG: PYTHON3
PROG: bcs
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=640
fin = open('bcs.in', 'r')
fout = open("bcs.out", "w")

N, K = map(int, fin.readline().strip().split())

pieces = []
sides = []
for i in range(K + 1):
    top = N - 1
    bottom = 0
    left = N - 1
    right = 0
    piece = [[False for _ in range(N)] for _ in range(N)]

    for r in range(N):
        row = fin.readline().strip()
        for c in range(N):
            piece[r][c] = row[c] == "#"
            if piece[r][c] is True:
                top = min(top, r)
                bottom = max(bottom, r)
                left = min(left, c)
                right = max(right, c)
    pieces.append(piece)
    sides.append((left, right, top, bottom))  # top has smaller row, left has smaller col

target = pieces[0]


def get_gaps(piece):
    gap_of_bottom = N - 1 - sides[piece][3]
    gap_of_top = sides[piece][2]
    gap_of_right = N - 1 - sides[piece][1]
    gap_of_left = sides[piece][0]
    return gap_of_left, gap_of_right, gap_of_top, gap_of_bottom


for i in range(1, K + 1):
    i_gap_of_left, i_gap_of_right, i_gap_of_top, i_gap_of_bottom = get_gaps(i)
    for j in range(i + 1, K + 1):
        j_gap_of_left, j_gap_of_right, j_gap_of_top, j_gap_of_bottom = get_gaps(j)
        for i_row_shift in range(-i_gap_of_top, i_gap_of_bottom + 1):
            for i_col_shift in range(-i_gap_of_left, i_gap_of_right + 1):
                for j_row_shift in range(-j_gap_of_top, j_gap_of_bottom + 1):
                    for j_col_shift in range(-j_gap_of_left, j_gap_of_right + 1):

                        # Move i piece
                        for r in range(N):
                            for c in range(N):
                                if pieces[i][r][c]:
                                    if not target[r + i_row_shift][c + i_col_shift]:
                                        good = False
                                        break

                        # Move j piece
                        for r in range(N):
                            for c in range(N):
                                if pieces[j][r][c]:
                                    # i already has a "#" in there
                                    if new_piece[r + j_row_shift][c + j_col_shift]:
                                        good = False
                                        break
                                    else:
                                        new_piece[r + j_row_shift][c + j_col_shift] = True

                        if not good:
                            continue
                        if new_piece == target:
                            fout.write(f"{i} {j}")
                            fout.close()
                            exit(0)