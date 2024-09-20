import heapq

n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
pq = []
for i in range(n):
    for j in range(m):
        if i == 0 or i == n - 1 or j == 0 or j == m - 1:
            heapq.heappush(pq, (-graph[i][j], i, j))
            graph[i][j] = 0
k = int(input())
cnt = 0
result = []
steps = [(1, 0), (0, 1), (-1, 0), (0, -1)]
while heapq:
    data, x, y = heapq.heappop(pq)
    result.append((x + 1, y + 1))
    cnt += 1
    if cnt == k:
        break
    for step in steps:
        nx, ny  = x + step[0], y + step[1]
        if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] != 0:
            heapq.heappush(pq, (-graph[nx][ny], nx, ny))
            graph[nx][ny] = 0
for i in range(len(result)):
    print(*result[i])