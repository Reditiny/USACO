"""
ID: mck15821
LANG: PYTHON3
PROG: transform
"""
fin = open('transform.in', 'r')
fout = open("transform.out", "w")
N = int(fin.readline().strip())
input_square = []
for i in range(N):
    line = fin.readline().strip()
    input_square.append([c for c in line])

output_square = []
for i in range(N):
    line = fin.readline().strip()
    output_square.append([c for c in line])


def rotate(square):
    result = []
    for i in range(N):
        result.append([""] * N)
    for i in range(N // 2):
        for j in range(i, N - i - 1):
            result[i][j] = square[j][N - 1 - i]
            result[j][N - 1 - i] = square[N - i - 1][N - j - 1]
            result[N - i - 1][N - j - 1] = square[N - j - 1][i]
            result[N - j - 1][i] = square[i][j]
    if N % 2 == 1:
        result[N // 2][N // 2] = square[N // 2][N // 2]
    return result


def reflect(square):
    result = []
    for i in range(N):
        result.append([""] * N)
    for i in range(N):
        for j in range(N // 2 + 1):
            result[i][j] = square[i][N - 1 - j]
            result[i][N - 1 - j] = square[i][j]
    return result


def check_equal(square1, square2):
    for i in range(len(square1)):
        for j in range(len(square1[0])):
            if square1[i][j] != square2[i][j]:
                return False
    return True


output = 7
if check_equal(rotate(input_square), output_square):
    output = 1
elif check_equal(rotate(rotate(input_square)), output_square):
    output = 2
elif check_equal(rotate(rotate(rotate(input_square))), output_square):
    output = 3
elif check_equal(reflect(input_square), output_square):
    output = 4
elif check_equal(rotate(reflect(input_square)), output_square) \
        or check_equal(rotate(rotate(reflect(input_square))), output_square) \
        or check_equal(rotate(rotate(rotate(reflect(input_square)))), output_square):
    output = 5
elif check_equal(input_square, output_square):
    output = 6

fout.write(str(output) + "\n")
fout.close()