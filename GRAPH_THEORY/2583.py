from collections import deque

n, m, k = map(int, input().split())
graph = [[0] * m for _ in range(n)]
for _ in range(k):
    x1, y1, x2, y2 = map(int, input().split())
    for i in range(y1, y2):
        for j in range(x1, x2):
            graph[i][j] = 1

result = []
steps = [(-1 ,0), (1, 0), (0, -1), (0, 1)]
def bfs(x, y):
    cnt = 1
    graph[x][y] = 1
    queue = deque([(x, y)])
    while queue:
        x, y = queue.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] == 0:
                graph[nx][ny] = 1
                cnt += 1
                queue.append((nx, ny))
    return cnt


cnt = 0
for i in range(n):
    for j in range(m):
        if graph[i][j] == 0:
            result.append(bfs(i, j))
            cnt += 1
print(cnt)
print(*sorted(result))