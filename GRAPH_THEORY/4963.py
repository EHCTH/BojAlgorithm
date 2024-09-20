from collections import deque

while True:
    cnt = 0
    m, n = map(int, input().split())
    if m == 0 and n == 0:
        break
    graph = [list(map(int, input().split())) for _ in range(n)]
    steps = [(-1, 0), (1, 0), (0, -1), (0, 1), (1, -1), (1, 1), (-1, 1), (-1, -1)]

    def bfs(x, y):
        queue = deque()
        graph[x][y] = 0
        queue.append((x, y))
        while queue:
            x, y = queue.popleft()
            for step in steps:
                nx, ny = x + step[0], y + step[1]
                if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] == 1:
                    graph[nx][ny] = 0
                    queue.append((nx, ny))
                
    for i in range(n):
        for j in range(m):
            if graph[i][j] == 1:
                bfs(i, j)
                cnt += 1
    print(cnt)