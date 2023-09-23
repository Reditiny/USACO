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

    while True:
        # check whether satisfy the requirement
        max_entry = max(entries)
        min_entry = min(entries)
        if max_entry == min_entry:
            return count * 2

        new_entries = entries.copy()
        i = 0
        while i < len(entries) - 2:
            # if entries[i] > entries[i + 1]:
            #     print("return when i > i + 1")
            #     return -1

            # entries[i] <= entries[i + 1], triplet of (lo, hi, lo), (hi, hi, lo), (lo, hi, hi), (hi, hi, hi)
            if entries[i] <= entries[i + 1] and entries[i + 1] >= entries[i + 2]:
                total_diff = entries[i + 1] - entries[i] + entries[i + 1] - entries[i + 2]
                count += total_diff
                new_level = entries[i + 1] - total_diff
                if new_level < 0:
                    return -1
                new_entries[i] = new_level
                new_entries[i + 1] = new_level
                new_entries[i + 2] = new_level
                i += 3
                continue

            # (lo, lo, hi), since the second lo cannot decrease with the third hi (otherwise the first lo is alone)
            # so bring lo to min value
            if entries[i] == entries[i + 1] and entries[i] > min_entry:
                count += entries[i] - min_entry
                new_entries[i] = min_entry
                new_entries[i + 1] = min_entry
                i += 2
                continue

            # (lo, mid, hi)
            i += 1
        entries = new_entries


main()
