from collections import deque
n, m = map(int ,input().split())
graph = [list(map(int ,input().split())) for _ in range(n)]
visited = [[False] * m for _ in range(n)]
steps = [(-1, 0), (1, 0), (0, 1), (0, -1)]
cnt = 0
all_cheese = sum([sum(item) for item in graph])
time_melting = {0 : all_cheese}
index = 0

def melting(new_visited):
    global cnt
    global all_cheese
    count = 0
    for i in range(n):
        for j in range(m):
            if new_visited[i][j]:
                for step in steps:
                    nx, ny = i + step[0], j + step[1]
                    if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] == 1:
                        graph[nx][ny] = 0
                        count += 1
    cnt += 1
    all_cheese -= count
    time_melting[cnt] = all_cheese
    return

def space(x, y):
    new_visited = [item[::] for item in visited]
    global index

    queue = deque()
    queue.append((x, y))
    new_visited[x][y] = True
    while queue:
        x, y = queue.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m and not new_visited[nx][ny] and graph[nx][ny] == 0:
                new_visited[nx][ny] = True
                queue.append((nx, ny))
    index += 1
    melting(new_visited)
    return





while True:
    for i in range(n):
        for j in range(m):
            if not visited[i][j] and graph[i][j] == 0 and all_cheese != 0:
                space(i, j)
    print(cnt)
    print(time_melting[cnt - 1])
    break