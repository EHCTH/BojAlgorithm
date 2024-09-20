import sys
sys.setrecursionlimit(100000)
n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]
steps = [(0, 1), (0, -1), (1, 0), (-1, 0)]
answer = 0


def dfs(x, y, h):
    visited[x][y] = True
    for step in steps:
        nx, ny = x + step[0], y + step[1]
        if 0 <= nx < n and 0 <= ny < n and not visited[nx][ny] and graph[nx][ny] > h:
            dfs(nx, ny, h)
            
for k in range(max(map(max, graph))):
    visited = [[False] * n for _ in range(n)]
    cnt = 0
    for i in range(n):
        for j in range(n):
            if graph[i][j] > k and not visited[i][j]:
                dfs(i, j, k)
                cnt += 1
    answer = max(answer, cnt)

print(answer)