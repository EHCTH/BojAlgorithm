import collections

n, m, r = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
group = []  # type: ignore
group_list = []
next_dict = []
for i in range(min(n, m) // 2):
    dict = {}
    idx = 0
    x, y = i, i
    dict[idx] = (x, y)
    for j in range(y, m - i):
        y = j
        dict[idx] = (x, y)
        idx += 1
        group.append(graph[x][y])
    for j in range(x + 1, n - i):
        x = j
        dict[idx] = (x, y)
        idx += 1
        group.append(graph[x][y])
    for j in range(y - 1, m - y - 2, - 1):
        y = j
        dict[idx] = (x, y)
        idx += 1
        group.append(graph[x][y])
    for j in range(x - 1, n - x - 1, -1):
        x = j
        dict[idx] = (x, y)
        idx += 1
        group.append(graph[x][y])
    next_dict.append(dict)
    group_list.append(group)
    group = []

for t in range((min(n, m)) // 2):
    rotate = r % (2 * ((n - (2 * t)) + (m - (2 * t))) - 4)
    group_list[t] = group_list[t][rotate:] + group_list[t][:rotate]
answer_graph = [[0] * m for _ in range(n)]
for i in range(len(group_list)):
    for key, value in next_dict[i].items():
        x, y = value
        answer_graph[x][y] = group_list[i][key]

for i in answer_graph:  # type: ignore
    print(*i)  # type: ignore

    #  12 , 4  (n - 1  - i) * 2  + (m - 1 - i) * 2 = 12
    #          (n -