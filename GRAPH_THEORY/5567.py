n = int(input())
m = int(input())
graph = [[] for _ in range(n + 1)]
for _ in range(m):
    a, b = map(int ,input().split())
    graph[a].append(b)
    graph[b].append(a)
visited = [False] * (n + 1)
visited[1] = True


def dfs(v, depth):
    if depth == 2:
        return
    for i in graph[v]:
        if not visited[i]:
            visited[i] = True
        dfs(i, depth + 1)


dfs(1, 0)
print(visited.count(True) - 1)