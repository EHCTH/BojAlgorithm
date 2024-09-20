# graph = [[], [2, 5], [3], [], [7], [2, 6], [], []]
n = int(input())
m = int(input())
graph = [[] for _ in range(n + 1)]
for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)
cnt = 0

visited = []
def virus(v):
    global cnt
    visited.append(v)
    for i in graph[v]:
        if i not in visited:
            cnt += 1
            virus(i)
    return cnt


print(virus(1))