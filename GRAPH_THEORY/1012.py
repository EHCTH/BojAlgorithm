from collections import deque

for T in range(int(input())):
    m, n, k = map(int, input().split())
    graph = [[0] * m for _ in range(n)]
    for _ in range(k):
        a, b = map(int, input().split())
        graph[b][a] = 1
    steps = [(-1, 0), (1, 0), (0, 1), (0, -1)]


    def bfs(x, y):
        queue = deque()
        queue.append((x, y))
        graph[x][y] = 0
        while queue:
            x, y = queue.popleft()
            for step in steps:
                nx, ny = x + step[0], y + step[1]
                if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] == 1:
                    graph[nx][ny] = 0
                    queue.append((nx, ny))

    cnt = 0
    for i in range(n):
        for j in range(m):
            if graph[i][j] == 1:
                bfs(i, j)
                cnt += 1
    print(cnt)