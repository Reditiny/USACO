"""
ID: mck15821
LANG: PYTHON3
PROG: lamps
"""
fin = open('lamps.in', 'r')
fout = open("lamps.out", "w")

MAX_LAMP = 6 # Pattern repeats every 6 lamps
N = int(fin.readline().strip())
C = int(fin.readline().strip())
on_list = list(map(int, fin.readline().strip().split()))
off_list = list(map(int, fin.readline().strip().split()))
visited = [0 for i in range(N)]
start_status_list = [1 for i in range(N)]
final_status_list = [0 for i in range(N)]
result_set = set()

for i in range(len(on_list) - 1):
    final_status_list[on_list[i] - 1] = 1
    visited[on_list[i] - 1] = 1

for i in range(len(off_list) - 1):
    visited[off_list[i] - 1] = 1

# Any even number of switch press greater than 4 is equivalent to 4 presses,
# because 2 presses of certain button equals to no press
# Any odd number of switch press greater than 3 is equivalent to 3 presses
if C > 4:
    C = 4 - (C % 2)


def match(cur_status_list):
    for i in range(len(cur_status_list)):
        if (cur_status_list[i] & visited[i]) != final_status_list[i]:
            return False
    return True


def generate_result(cur_status_list):
    s = ""
    for d in cur_status_list:
        s += str(d)
    return s


def button_1(cur_status_list):
    result = []
    for i in range(len(cur_status_list)):
        result.append(1 - cur_status_list[i])
    return result


def button_2(cur_status_list):
    result = []
    for i in range(len(cur_status_list)):
        if i % 2 == 0:
            result.append(1 - cur_status_list[i])
        else:
            result.append(cur_status_list[i])
    return result


def button_3(cur_status_list):
    result = []
    for i in range(0, len(cur_status_list)):
        if i % 2 == 1:
            result.append(1 - cur_status_list[i])
        else:
            result.append(cur_status_list[i])
    return result


def button_4(cur_status_list):
    result = []
    for i in range(len(cur_status_list)):
        if i % 3 == 0:
            result.append(1 - cur_status_list[i])
        else:
            result.append(cur_status_list[i])
    return result


def search(cur_status_list, target):
    if target == 0:
        result_s = generate_result(cur_status_list)
        if result_s not in result_set and match(cur_status_list):
            result_set.add(result_s)
        return
    search(button_1(cur_status_list), target - 1)
    search(button_2(cur_status_list), target - 1)
    search(button_3(cur_status_list), target - 1)
    search(button_4(cur_status_list), target - 1)


search(start_status_list, C)
result_list = list(result_set)
result_list.sort()
if len(result_list) == 0:
    fout.write("IMPOSSIBLE\n")
else:
    for s in result_list:
        fout.write(f"{s}\n")

fout.close()
