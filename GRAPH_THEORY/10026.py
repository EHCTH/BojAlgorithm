import sys
sys.setrecursionlimit(1000000)
n = int(sys.stdin.readline())
graph = [list(sys.stdin.readline().rstrip()) for _ in range(n)]
visited = [[False] * n for _ in range(n)]

steps = [(0, 1), (0, -1), (1, 0), (-1, 0)]
def dfs(x, y):
    visited[x][y] = True
    color = graph[x][y]
    if graph[x][y] == 'R':
        graph[x][y] = 'G'
    for step in steps:
        nx, ny = x + step[0], y + step[1]
        if 0 <= nx < n and 0 <= ny < n and not visited[nx][ny] and graph[nx][ny] == color:
            dfs(nx, ny)

def dfs2(x, y):
    visited[x][y] = False
    color = graph[x][y]
    for step in steps:
        nx, ny = x + step[0], y + step[1]
        if 0 <= nx < n and 0 <= ny < n and visited[nx][ny] and graph[nx][ny] == color:
            dfs2(nx, ny)

cnt, cnt2 = 0, 0
for i in range(n):
    for j in range(n):
        if not visited[i][j]:
            dfs(i, j)
            cnt += 1
for i in range(n):
    for j in range(n):
        if visited[i][j]:
            dfs2(i, j)
            cnt2 += 1
print(cnt, cnt2)