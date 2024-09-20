from collections import deque

n = int(input())
graph = []
for i in range(n):
    graph.append(list(map(int, input())))
steps = [(-1, 0),(1, 0),(0, -1),(0, 1)]

def bfs(x, y):
    visited = []
    queue = deque()
    visited.append((x, y))
    queue.append((x, y))
    graph[x][y] = 0
    cnt = 1
    while queue:
        x, y = queue.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < n and graph[nx][ny] == 1:
                    graph[nx][ny] = 0
                    queue.append((nx, ny))
                    cnt += 1
    return cnt

cnt = 0
answer = []
for i in range(n):
    for j in range(n):
        if graph[i][j] == 1:
            answer.append(bfs(i, j))
            cnt += 1
print(cnt)
for i in sorted(answer):
    print(i)