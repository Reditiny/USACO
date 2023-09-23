fin = open("mixmilk.in", "r")
fout = open("mixmilk.out", "w")

number_of_buckets = 3
capacity = [0, 0, 0]  # capacity of each bucket
volume = [0, 0, 0]  # volume of each bucket

for i in range(3):
    c, v = map(int, fin.readline().strip().split())
    capacity[i] = c
    volume[i] = v

for times in range(100):
    # pour A -> B
    # B has enough space; A -> 0, B's volume + A's volume
    source_bucket = times % 3
    target_bucket = (times + 1) % 3

    if volume[source_bucket] < capacity[target_bucket] - volume[target_bucket]:
        volume[target_bucket] += volume[source_bucket]
        volume[source_bucket] = 0

    # B doesn't have enough space; A -> A volume - (B capacity - B volume), B -> capacity
    else:
        volume[source_bucket] -= capacity[target_bucket] - volume[target_bucket]
        volume[target_bucket] = capacity[target_bucket]


for i in range(3):
    fout.write(f"{volume[i]}\n")
fout.close()