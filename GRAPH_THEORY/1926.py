import collections

n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
steps = [(-1, 0), (0 , 1), (0, -1), (1, 0)]
def bfs(x, y):
    queue = collections.deque()
    queue.append((x, y))
    graph[x][y] = 0
    cnt = 1
    while queue:
        x, y = queue.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] == 1:
                graph[nx][ny] = 0
                cnt += 1
                queue.append((nx, ny))
    return cnt
cnt = 0
max_num = 0
for i in range(n):
    for j in range(m):
        if graph[i][j] == 1:
            max_num = max(max_num, bfs(i, j))
            cnt += 1
print(cnt)
print(max_num)