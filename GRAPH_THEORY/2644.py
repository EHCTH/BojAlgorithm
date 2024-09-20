n = int(input())
p1, p2 = map(int ,input().split())
m = int(input())
visited = [0] * (n + 1)
graph = [[] * (n + 1) for _ in range(n + 1)]
for _ in range(m):
    x, y = map(int, input().split())
    graph[x].append(y)
    graph[y].append(x)
result = []
def dfs(v, cnt):
    cnt += 1
    visited[v] = 1
    if v == p2:
        result.append(cnt)
    for i in graph[v]:
        if visited[i] == 0:
            dfs(i, cnt)

dfs(p1, 0)
if not result:
    print(-1)
else:
    print(result[0] - 1)