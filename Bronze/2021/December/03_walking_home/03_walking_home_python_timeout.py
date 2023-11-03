"""
ID: mck15821
LANG: PYTHON3
PROG: Walking Home
"""
# Time complexity: O(V * N), V as maximum difference
# http://usaco.org/index.php?page=viewproblem2&cpid=1157
T = int(input())

for _ in range(T):
    N, K = map(int, input().strip().split())
    grid = []
    for x in range(N):
        grid.append(input())
    # if K == 1 meaning only turn at top right or bottom left
    answer = 0
    if K >= 1:
        ur_corner, bl_corner = True, True
        for i in range(N):
            if grid[0][i] == "H" or grid[i][N - 1] == "H":
                ur_corner = False
            if grid[N - 1][i] == "H" or grid[i][0] == "H":
                bl_corner = False
        answer += ur_corner
        answer += bl_corner
    if K >= 2:
        for j in range(1, N - 1):
            valid = True
            for i in range(N):
                if grid[i][j] == "H":
                    valid = False
                # column i is before j, and row 0 has a hole
                elif i < j and grid[0][i] == "H":
                    valid = False
                # column i is after j, and row N-1 has a hole
                elif i > j and grid[N - 1][i] == "H":
                    valid = False
            answer += valid

        for i in range(1, N - 1):
            valid = True
            for j in range(N):
                if grid[i][j] == "H":
                    valid = False
                elif j < i and grid[j][0] == "H":
                    valid = False
                elif j > i and grid[j][N - 1] == "H":
                    valid = False
            answer += valid
    if K >= 3:
        for i in range(1, N - 1):
            for j in range(1, N - 1):
                # RDRD
                valid = grid[i][j] == "."
                for k in range(N):
                    if k <= i and grid[k][j] == "H":
                        valid = False
                    if k <= j and grid[0][k] == "H":
                        valid = False

                    if k >= j and grid[i][k] == "H":
                        valid = False
                    if k >= i and grid[k][N - 1] == "H":
                        valid = False

                answer += valid
                # DRDR
                valid = grid[i][j] == "."
                for k in range(N):
                    if k <= i and grid[k][0] == "H":
                        valid = False
                    if k <= j and grid[i][k] == "H":
                        valid = False

                    if k >= i and grid[k][j] == "H":
                        valid = False
                    if k >= j and grid[N - 1][k] == "H":
                        valid = False

                answer += valid
    print(answer)
