import collections
n = int(input())
graph = [[] for _ in range(n + 1)]
for _ in range(n -1):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))
    graph[b].append((a, c))


def bfs(x):
    distance = [-1 for _ in range(n + 1)]
    queue = collections.deque()
    distance[x] = 0
    queue.append(x)
    while queue:
        v = queue.popleft()
        for child, weight in graph[v]:
            if distance[child] == -1:
                distance[child] = distance[v] + weight
                queue.append(child)
    return max(distance), distance.index(max(distance))

answer, next_index = bfs(1)
answer, next_index = bfs(next_index)
print(answer)