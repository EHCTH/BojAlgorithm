from collections import deque
n, m, v = map(int, input().split())
graph = [[] for _ in range(n + 1)]


for i in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

visited = []
def dfs(v):
    visited.append(v)
    print(v, end=" ")
    for i in sorted(graph[v]):
        if i not in visited:
            dfs(i)


def bfs(v):
    visited = []
    queue = deque()
    queue.append(v)
    visited.append(v)
    while queue:
        s = queue.popleft()
        print(s, end=" ")
        for i in sorted(graph[s]):
            if i not in visited:
                queue.append(i)
                visited.append(i)
dfs(v)
print()
bfs(v)