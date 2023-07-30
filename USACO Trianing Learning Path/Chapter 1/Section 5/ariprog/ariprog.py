"""
ID: mck15821
LANG: PYTHON3
PROG: ariprog
"""

fin = open('ariprog.in', 'r')
fout = open("ariprog.out", "w")
sequence_length = int(fin.readline().strip())
limit = int(fin.readline().strip())
max_number = limit * limit * 2
bisquares = [False for i in range(max_number + 1)]
for i in range(limit + 1):
    for j in range(limit + 1):
        bisquares[i * i + j * j] = True

result = []

for start in range(max_number):
    # start must be a bisquare
    if not bisquares[start]:
        continue
    # need to form a sequence of bisquare at length sequence_length
    # start is the first number, need another sequence_length - 1
    for diff in range(1, (max_number - start) // (sequence_length - 1) + 1):
        valid = True
        for count in range(1, sequence_length):
            if not bisquares[start + count * diff]:
                valid = False
                break
        if not valid:
            continue
        result.append([diff, start])

if len(result) == 0:
    fout.write("NONE\n")
else:
    result = sorted(result, key=lambda x: tuple(x))

    for pair in result:
        fout.write(f"{pair[1]} {pair[0]}\n")
fout.close()