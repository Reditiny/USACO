"""
ID: mck15821
LANG: PYTHON3
PROG: ariprog
"""

# fin = open('ariprog.in', 'r')
# fout = open("ariprog.out", "w")
sequence_length = int(fin.readline().strip())
limit = int(fin.readline().strip())
max_number = limit * limit * 2
bisquares_set = set()
for i in range(limit + 1):
    for j in range(limit + 1):
        bisquares_set.add(i * i + j * j)

result = []


bisquares_list = list(bisquares_set)
bisquares_list.sort()

# first number
for i in range(len(bisquares_list) - sequence_length + 1):
    # need to form a sequence of bisquare at length sequence_length
    # start is the first number, need another sequence_length - 1
    # second number
    for j in range(i+1, len(bisquares_list) - sequence_length + 2):
        diff = bisquares_list[j] - bisquares_list[i]
        valid = True
        for count in range(sequence_length - 2):
            if (bisquares_list[j] + count * diff) not in bisquares_set:
                valid = False
                break

        if not valid:
            continue
        result.append([diff, bisquares_list[i]])

if len(result) == 0:
    fout.write("NONE\n")
else:
    result = sorted(result, key=lambda x: tuple(x))

    for pair in result:
        fout.write(f"{pair[1]} {pair[0]}\n")
fout.close()

