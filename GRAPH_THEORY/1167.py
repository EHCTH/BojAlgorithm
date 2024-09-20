import collections

# 5
# 1 3 2 -1
# 2 4 4 -1
# 3 1 2 4 3 -1
# 4 2 4 3 3 5 6 -1
# 5 4 6 -1
v = int(input())
graph = [[] for _ in range(v + 1)]
for _ in range(v):
    data = list(map(int, input().split()))
    for i in range(1, len(data) - 2, 2):
        graph[data[0]].append((data[i], data[i + 1]))
# # print(graph)
# graph =[[], [(3, 2)], [(4, 4)], [(1, 2), (4, 3)], [(2, 4), (3, 3), (5, 6)], [(4, 6)]]
def bfs(x):
    distance = [-1 for _ in range(v + 1)]
    queue = collections.deque()
    queue.append(x)
    distance[x] = 0
    while queue:
        x = queue.popleft()
        for edge, weight in graph[x]:
            if distance[edge] == -1:
                distance[edge] = weight + distance[x]
                queue.append(edge)
    return max(distance), distance.index(max(distance))

answer, next_node = bfs(1)
answer, next_node = bfs(next_node)
print(answer)