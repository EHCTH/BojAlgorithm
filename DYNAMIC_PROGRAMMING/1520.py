n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
steps = [(-1, 0), (1, 0), (0, -1), (0, 1)]
dp = [[0] * m for _ in range(n)]
dp[0][0] = 1
queue = [(-graph[0][0], 0, 0)]

def bfs(queue):
    while queue:
        queue.sort(key=lambda x: x[0])
        data, x, y = queue.pop(0)
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m and graph[x][y] > graph[nx][ny]:
                if dp[nx][ny] == 0:
                    queue.append((-graph[nx][ny], nx, ny))
                dp[nx][ny] += dp[x][y]
    return dp[n - 1][m - 1]
print(bfs(queue))