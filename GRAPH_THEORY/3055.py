import collections

n, m = map(int, input().split())
steps = [(-1, 0), (1, 0), (0, -1), (0, 1)]
distance = [[-1] * m for _ in range(n + 1)]
graph = [list(input()) for _ in range(n)]
data = []
for i in range(n):
    for j in range(m):
        if graph[i][j] == 'S':
            data.append((i, j))
            distance[i][j] = 0
for i in range(n):
    for j in range(m):
        if graph[i][j] == '*':
            data.append((i, j))


def move(data):
    queue = collections.deque(data)
    while queue:
        x, y = queue.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m:
                if graph[x][y] == 'S':
                    if graph[nx][ny] == ".":
                        graph[nx][ny] = "S"
                        distance[nx][ny] = distance[x][y] + 1
                        queue.append((nx, ny))
                    if graph[nx][ny] == "D":
                        return distance[x][y] + 1
                elif (graph[nx][ny] == '.' or graph[nx][ny] == 'S') and graph[x][y] == '*':
                    graph[nx][ny] = "*"
                    queue.append((nx, ny))
    return "KAKTUS"


print(move(data))