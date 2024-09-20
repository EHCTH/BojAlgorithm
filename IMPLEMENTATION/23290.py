import copy

n = 4
m, s = map(int, input().split())
move_dir = [(0, -1), (-1, -1), (-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1)]
graph = [[[[] for _ in range(n)] for _ in range(n)] for _ in range(3)]
graph[1] = [[0] * n for _ in range(n)]
graph[2] = [[0] * n for _ in range(n)]
steps = [(-1, 0), (0, -1), (1, 0), (0, 1)]
# 3차원 그래프 0은 물고기 위치, 1은 상어 위치, 2는 냄새
for _ in range(m):
    fish_x, fish_y, fish_dir = map(int, input().split())
    graph[0][fish_x - 1][fish_y - 1].append(fish_dir - 1)
shark_x, shark_y = map(int, input().split())
shark_coordinate = (shark_x - 1, shark_y - 1)
graph[1][shark_coordinate[0]][shark_coordinate[1]] = 1
max_eat_graph = []
cur_eat = []
next_shark_coordinate = []
max_eat_fish = -1e9


# # ←, ↖, ↑, ↗, →, ↘, ↓, ↙
def rotate_and_add_tmp_graph(x, y, fish_cur_move, graph):
    rotate_cnt = 8
    while rotate_cnt:
        rotate_cnt -= 1
        nx, ny = x + move_dir[fish_cur_move][0], y + move_dir[fish_cur_move][1]
        if 0 <= nx < n and 0 <= ny < n:
            if not graph[1][nx][ny] and not graph[2][nx][ny]:
                return nx, ny, fish_cur_move
        fish_cur_move = (fish_cur_move + 7) % 8
    return x, y, fish_cur_move


def fish_move(graph):
    tmp_graph = [[[] for _ in range(n)] for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if graph[0][i][j]:
                while graph[0][i][j]:
                    fish_cur_move = graph[0][i][j].pop()
                    x, y, fish_cur_move = rotate_and_add_tmp_graph(i, j, fish_cur_move, graph)
                    tmp_graph[x][y].append(fish_cur_move)
    return tmp_graph


def shark_eating_fish(max_eat_graph, cur_shark_coordinate, copy_graph):
    global next_shark_coordinate
    copy_graph[1][cur_shark_coordinate[0]][cur_shark_coordinate[1]] = 0
    copy_graph[1][next_shark_coordinate[0]][next_shark_coordinate[1]] = 1
    for x, y in max_eat_graph:
        copy_graph[0][x][y] = []
        copy_graph[2][x][y] = 3
    return next_shark_coordinate, remove_smell(copy_graph)


def remove_smell(graph):
    for i in range(n):
        for j in range(n):
            if graph[2][i][j]:
                graph[2][i][j] -= 1
    return graph


def add_graph(copy_graph, graph):
    for i in range(n):
        for j in range(n):
            copy_graph[0][i][j] += graph[0][i][j]
    return copy_graph


def shark_move(N, graph, x, y, cnt):
    global max_eat_graph, cur_eat, next_shark_coordinate, max_eat_fish
    if N == 3:
        if cnt > max_eat_fish:
            max_eat_fish = cnt
            max_eat_graph = cur_eat[::]
            next_shark_coordinate = (x, y)
        return max_eat_graph, next_shark_coordinate
    for step in steps:
        nx, ny = x + step[0], y + step[1]
        if 0 <= nx < n and 0 <= ny < n:
            if (nx, ny) not in cur_eat and graph[0][nx][ny]:
                cur_eat.append((nx, ny))
                max_eat_graph, next_shark_coordinate = shark_move(N + 1, graph, nx, ny, \
                                                         cnt + len(graph[0][nx][ny]))
                cur_eat.pop()
            else:
                max_eat_graph, next_shark_coordinate = shark_move(N + 1, graph, nx, ny, cnt)
    return max_eat_graph, next_shark_coordinate
def init():
    global max_eat_graph, cur_eat, next_shark_coordinate, max_eat_fish
    max_eat_graph = []
    cur_eat = []
    next_shark_coordinate = []
    max_eat_fish = -1e9

def solutions(s, graph, shark_coordinate):
    global max_eat_graph, cur_eat, next_shark_coordinate, max_eat_fish
    for _ in range(s):
        copy_graph = copy.deepcopy(graph)
        copy_graph[0] = fish_move(copy_graph)
        init()
        cnt = 0

        max_eat_graph, next_shark_coordinate = shark_move(0, copy_graph, shark_coordinate[0],\
                                                          shark_coordinate[1], cnt)

        shark_coordinate, copy_graph = shark_eating_fish(max_eat_graph, shark_coordinate,  copy_graph)
        graph = add_graph(copy_graph, graph)

    return graph[0]


answer = 0
graph = solutions(s, graph, shark_coordinate)
for i in range(n):
    for j in range(n):
        answer += len(graph[i][j])
print(answer)