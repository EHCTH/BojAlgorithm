from collections import deque
m, n, h = map(int, input().split())
graph = [[] for _ in range(h)]
for z in range(h):
    for _ in range(n):
        graph[z].append(list(map(int, input().split())))
steps = [(0, -1, 0), (0, 1, 0), (0, 0, 1), (0, 0, -1), (1, 0, 0), (-1, 0, 0)] # z, x y
answer = 0
data = []
def bfs(data):
    queue = deque(data)
    max_num = 1
    while queue:
        z, x, y = queue.popleft()
        for step in steps:
            nz, nx, ny = z + step[0], x + step[1], y + step[2]
            if 0 <= nz < h and 0 <= nx < n and 0 <= ny < m and graph[nz][nx][ny] == 0:
                graph[nz][nx][ny] = graph[z][x][y] + 1
                queue.append((nz, nx, ny))
                max_num = max(max_num, graph[nz][nx][ny])
    return max_num - 1

for k in range(h):
    for i in range(n):
        for j in range(m):
            if graph[k][i][j] == 1:
                data.append((k, i, j))


answer += bfs(data)

for k in graph:
    for i in k:
        if 0 in i:
            answer = -1
print(answer)