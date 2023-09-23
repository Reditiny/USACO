"""
ID: mck15821
LANG: PYTHON3
PROG: Sleeping in Class
"""
# http://usaco.org/index.php?page=viewproblem2&cpid=1203
T = int(input())
for _ in range(T):
    number_of_entries = int(input())
    entries = list(map(int, input().split()))
    count = 0
    while True:
        # check whether satisfy the requirement
        max_entry = max(entries)
        min_entry = min(entries)
        if max_entry == min_entry:
            print(count)
            break

        new_entries = []
        i = 0
        while i < len(entries):
            if entries[i] == max_entry:
                new_entries.append(entries[i])
                i += 1
                continue
            if i < len(entries) - 1:
                new_entries.append(entries[i] + entries[i + 1])
                i += 2
                count += 1
            else:
                new_entries.append(entries[i])
                i += 1

        # need to set a new max number if nothing changed
        if entries == new_entries:
            num_1 = new_entries.pop(0)
            num_2 = new_entries.pop(0)
            new_entries.insert(0, num_1 + num_2)
            count += 1
        entries = new_entries
