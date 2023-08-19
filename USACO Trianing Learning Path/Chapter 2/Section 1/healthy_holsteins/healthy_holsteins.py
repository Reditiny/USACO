"""
ID: mck15821
LANG: PYTHON3
PROG: holstein
"""
fin = open('holstein.in', 'r')
fout = open("holstein.out", "w")


# feed_count: number of feed in current mixture, feed_id: last feed trying to add
def find_feed(feed_count, feed_id, best, best_feed_list, cur_feed_list):
    v = 0
    while v < number_of_vitamins:
        if requirements[v] > 0:
            break
        v += 1
    # all requirements met
    if v >= number_of_vitamins:
        best.clear()
        best.append(feed_count)
        best_feed_list.clear()
        for cur_feed in cur_feed_list:
            best_feed_list.append(cur_feed)
        return

    # trying adding one feed to the mixture
    while feed_id < number_of_feeds and feed_count + 1 < best[0]:
        for i in range(number_of_vitamins):
            requirements[i] -= feeds[feed_id][i]

        cur_feed_list.append(feed_id)

        find_feed(feed_count + 1, feed_id + 1, best, best_feed_list, cur_feed_list)

        # undo adding the vitamins
        for i in range(number_of_vitamins):
            requirements[i] += feeds[feed_id][i]
        cur_feed_list.pop()

        feed_id += 1


number_of_vitamins = int(fin.readline().strip())
requirements = list(map(int, fin.readline().strip().split()))
number_of_feeds = int(fin.readline().strip())
feeds = []
for _ in range(number_of_feeds):
    feeds.append(list(map(int, fin.readline().strip().split())))

best = [number_of_feeds + 1]
best_feed_list = []

find_feed(0, 0, best, best_feed_list, [])

fout.write(f"{best[0]} ")
for i in range(len(best_feed_list)):
    fout.write(f"{best_feed_list[i] + 1}")
    if i != len(best_feed_list) - 1:
        fout.write(" ")
fout.write("\n")
fout.close()
