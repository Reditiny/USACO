"""
ID: mck15821
LANG: PYTHON3
PROG: Drought
"""


# http://usaco.org/index.php?page=viewproblem2&cpid=1181
def main():
    T = int(input())
    for _ in range(T):
        number_of_entries = int(input())
        entries = list(map(int, input().split()))
        result = get_count(entries)
        print(result)


def get_count(entries):
    count = 0
    if len(entries) == 1:
        return 0
    if len(entries) == 2:
        if entries[0] != entries[1]:
            return -1
        return 0

    i = 0
    while i < len(entries) - 1:
        # print(i, count, entries)
        if entries[i] < entries[i + 1]:
            if i < len(entries) - 2:
                diff = entries[i + 1] - entries[i]
                entries[i + 1] -= diff
                entries[i + 2] -= diff
                count += 2 * diff
                if entries[i + 2] < 0:
                    return -1
            else:
                return -1

        elif entries[i] > entries[i + 1]:
            if i % 2 == 0:
                return -1
            diff = entries[i] - entries[i + 1]
            for j in range(i + 1):
                entries[j] = entries[i + 1]
            count += (i + 1) * diff

        i += 1

    return count


main()
