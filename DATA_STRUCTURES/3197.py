import collections

n, m = map(int, input().split())
graph =[list(input()) for _ in range(n)]

data = []
swan = []
for i in range(n):
    for j in range(m):
        if graph[i][j] == '.':
            data.append((i, j))
        if graph[i][j] == 'L':
            data.append((i, j))
            swan.append((i, j))

steps = [(-1, 0), (1, 0), (0, 1), (0, -1)]
distance = [[0] * m for _ in range(n)]
move_swan = [collections.deque() for _ in range(3)]
distance[swan[0][0]][swan[0][1]] = 1
distance[swan[1][0]][swan[1][1]] = 2
for i in range(2):
    move_swan[i + 1].append(swan[i])

find = False
t = 0
def check_swan(move_swan):
    global find, t
    next_move_swan = [collections.deque() for _ in range(3)]
    for i in range(1, 3):
        while move_swan[i]:
            x, y = move_swan[i].popleft()
            for step in steps:
                nx, ny = x + step[0], y + step[1]
                if 0 <= nx < n and 0 <= ny < m and distance[nx][ny] != i:
                    if graph[nx][ny] == '.':
                        distance[nx][ny] = i
                        move_swan[i].append((nx, ny))
                    elif graph[nx][ny] == 'X':
                        distance[nx][ny] = i
                        next_move_swan[i].append((nx, ny))
                    else:
                        print(t)
                        exit(0)

    return next_move_swan



def solutions(data):
    queue = collections.deque(data)
    next_queue = []
    while queue:
        x, y = queue.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] == 'X':
                graph[nx][ny] = '.'
                next_queue.append((nx, ny))
    return next_queue


while True:
    move_swan = check_swan(move_swan)
    data = solutions(data)
    t += 1