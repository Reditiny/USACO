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
    answer = 0

    # Each point check number of path from start * number of path to goal
    def count_number_of_path(r, c, grid):
        if grid[r][c] == "H":
            return 0

        count_to_goal = 0

        # DR
        if r < N - 1:
            valid = True
            for k in range(r + 1, N):
                if grid[k][c] == "H":
                    valid = False
                    break
            for k in range(c + 1, N):
                if grid[N - 1][k] == "H":
                    valid = False
                    break
            count_to_goal += valid

        # RD
        if c < N - 1:
            valid = True
            for k in range(c + 1, N):
                if grid[r][k] == "H":
                    valid = False
                    break
            for k in range(r + 1, N):
                if grid[k][N - 1] == "H":
                    valid = False
                    break
            count_to_goal += valid

        count_from_start = 0
        # UL
        if r > 0:
            valid = True
            for k in range(r):
                if grid[k][c] == "H":
                    valid = False
                    break
            for k in range(c):
                if grid[0][k] == "H":
                    valid = False
                    break
            count_from_start += valid

        # LU
        if c > 0:
            valid = True
            for k in range(c):
                if grid[r][k] == "H":
                    valid = False
                    break
            for k in range(r):
                if grid[k][0] == "H":
                    valid = False
                    break
            count_from_start += valid
        print(count_from_start, count_to_goal)
        return count_from_start * count_to_goal


    if K >= 1:
        # check top right point and bottom left point
        answer += count_number_of_path(0, N - 1, grid)
        answer += count_number_of_path(N - 1, 0, grid)
    if K >= 2:
        # check [0, 1] -> [0, N - 2], [1, 0] -> [N - 2, 0]
        for i in range(1, N - 1):
            answer += count_number_of_path(N - 1, i, grid)
            answer += count_number_of_path(i, N - 1, grid)
    if K >= 3:
        for i in range(1, N - 1):
            for j in range(1, N - 1):
                answer += count_number_of_path(i, j, grid)
    print(answer)
    if _ == 1:
        break
