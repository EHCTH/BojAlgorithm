import sys
from collections import deque
sys.setrecursionlimit(10**4)
steps = [(-1, 0), (1, 0), (0, -1), (0, 1)]
n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
cnt = 0
time = 0

def iceberg_search(x, y, visited):
    queue = deque()
    queue.append((x, y))
    visited[x][y] = True
    while queue:
        x, y = queue.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] != 0 and not visited[nx][ny]:
                visited[nx][ny] = True
                queue.append((nx, ny))
def bfs(x, y):
    visited = [[False] * m for _ in range(n)]
    queue = deque()
    queue.append((x, y))
    melting = [[0] * m for _ in range(n)]
    visited[x][y] = True
    while queue:
        x, y = queue.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m:
                if graph[nx][ny] == 0 and melting[x][y] < graph[x][y]:
                    melting[x][y] += 1
                elif graph[nx][ny] != 0 and not visited[nx][ny]:
                    visited[nx][ny] = True
                    queue.append((nx, ny))

    for i in range(n):
        for j in range(m):
            graph[i][j] -= melting[i][j]
    cnt = 0
    iceberg_visited = [[False] * m for _ in range(n)]
    for i in range(n):
        for j in range(m):
            if graph[i][j] != 0 and not iceberg_visited[i][j]:
                iceberg_search(i, j, iceberg_visited)
                cnt += 1
    return time + 1, cnt


while True:
    for i in range(n):
        for j in range(m):
            if graph[i][j] != 0:
                time, iceberg = bfs(i, j)
                if 1 < iceberg:
                    print(time)
                    exit(0)
    print(0)
    break