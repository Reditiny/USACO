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


def check(piece, x, y):
    return 0 <= x < N and 0 <= y < N and piece[x][y]


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
                        good = True
                        for r in range(N):
                            for c in range(N):
                                # The current pixel [r, c] can come from [r - i_row_shift, c - i_col_shift]
                                # and [r - j_row_shift, c - j_col_shift]
                                # For pieces[i] the pixel moves i_row_shift to r, as r = r_pieces[i] + i_row_shift
                                # So reversely, the r_pieces[i] = r - i_row_shift
                                pieces_i_original_r, pieces_i_original_c = r - i_row_shift, c - i_col_shift
                                pieces_j_original_r, pieces_j_original_c = r - j_row_shift, c - j_col_shift

                                pieces_i_value = check(pieces[i], pieces_i_original_r, pieces_i_original_c)
                                pieces_j_value = check(pieces[j], pieces_j_original_r, pieces_j_original_c)

                                if pieces_i_value and pieces_j_value:
                                    good = False
                                    break

                                if target[r][c] != (pieces_i_value or pieces_j_value):
                                    good = False
                                    break

                            if not good:
                                break
                        if good:
                            fout.write(f"{i} {j}")
                            fout.close()
                            exit(0)
