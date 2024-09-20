import collections

n = 4
graph = [[(-1, -1)] * (n + 2) for _ in range(n + 2)]
steps = [(-1, 0), (-1, -1), (0, -1), (1, -1), (1, 0), (1, 1), (0, 1), (-1, 1)]

for i in range(1, n + 1):
    index = 0
    li = list(map(int, input().split()))
    for j in range(1, n + 1):
        graph[i][j] = tuple(li[index:index + 2])  # type: ignore
        index += 2
queue = collections.deque()
max_value = -1e9
def shark_move(shark, graph):
    queue = collections.deque(shark)
    while queue:
        x, y, dir = queue.popleft()
        food_list = []
        nx, ny = x, y
        while graph[nx + steps[dir - 1][0]][ny + steps[dir - 1][1]][0] >= 0:
            if graph[nx + steps[dir - 1][0]][ny + steps[dir - 1][1]][0] == 0:
                nx += steps[dir - 1][0]
                ny += steps[dir - 1][1]
                continue
            food_list.append((nx + steps[dir - 1][0], ny + steps[dir - 1][1]))
            nx += steps[dir - 1][0]
            ny += steps[dir - 1][1]
    return food_list


def fish_move(graph):
    move_data = []
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            if graph[i][j][0] != -1 and graph[i][j][0] != 0:
                move_data.append((i, j, graph[i][j][0], graph[i][j][1]))
    cnt = 1
    while cnt != 17:
        is_extend = False
        for i in range(1, n + 1):
            if is_extend:
                break
            for j in range(1, n + 1):
                if is_extend:
                    break
                if graph[i][j][0] == cnt:
                    dir = graph[i][j][1]
                    while True:
                        tmp = graph[i][j]
                        if graph[i + steps[dir - 1][0]][j + steps[dir - 1][1]][0] >= 0:
                            graph[i][j] = graph[i + steps[dir - 1][0]][j + steps[dir - 1][1]]
                            graph[i + steps[dir - 1][0]][j + steps[dir - 1][1]] = (tmp[0], dir)
                            is_extend = True
                            break
                        else:
                            dir = (dir + 1) % 8
        cnt += 1
    return graph

def dfs(x, y, graph, answer):
    global max_value
    new_graph = [item[::] for item in graph]
    shark = [(x, y, new_graph[x][y][1])]
    answer += new_graph[x][y][0]
    new_graph[x][y] = (-1, -1)
    new_graph = fish_move(new_graph)
    food_list = shark_move(shark, new_graph)
    if not food_list:
        max_value = max(max_value, answer)
        return
    for nx, ny, in food_list:
        new_graph[x][y] = (0, 0)
        dfs(nx, ny, new_graph, answer)
        new_graph[x][y] = (-1, -1)


dfs(1, 1, graph, 0)
print(max_value)