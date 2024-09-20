n, m = map(int, input().split())
visited = [False] * (n + 1)
graph = [[] for _ in range(n + 1)]
# 6, 3
# 1 2
# 3 6
# 4 5
# answer = 3
for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

def dfs(v):
    visited[v] = True
    for i in graph[v]:
        if not visited[i]:
            dfs(i)

cnt = 0
for i in range(1, n + 1):
    if not visited[i]:
        dfs(i)
        cnt += 1
print(cnt)