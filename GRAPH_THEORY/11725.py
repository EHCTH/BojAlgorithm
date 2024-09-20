import sys
sys.setrecursionlimit(10**6)
n = int(input())
graph = [[] for _ in range(n + 1)]
for _ in range(n - 1):
    a, b = map(int ,input().split())
    graph[a].append(b)
    graph[b].append(a)

visited = [-1 for _ in range(n + 1)]
def dfs(v):
    for i in graph[v]:
        if visited[i] == -1:
            visited[i] = v
            dfs(i)
dfs(1)
print(*visited[2:], sep="\n")