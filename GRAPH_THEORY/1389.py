from collections import deque

n, m = map(int, input().split())
graph = [[] * (m) for _ in range(n + 1)]
result = [[] for _ in range(n + 1)]

for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)
def bfs(data):
    distance = [0 for _ in range(n + 1)]
    visited = [False for _ in range(n + 1)]
    visited[data] = True
    queue = deque()
    queue.append(data)
    while queue:
        v = queue.popleft()
        for i in graph[v]:
            if not visited[i]:
                distance[i] = distance[v] + 1
                visited[i] = True
                queue.append(i)
    return sum(distance)

for i in range(1, n + 1):
    result[i].append(bfs(i))
min_player = result[1]
index = 1
for i in range(2, n + 1):
    if result[i] < min_player:
        min_player = result[i]
        index = i
print(index)