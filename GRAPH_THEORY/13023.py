N, M = map(int, input().split())
graph = [[] for _ in range(N)]
for i in range(M):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)
visited = [False] * N

def solutions(n, idx):
    if n == 4:
        print(1)
        exit(0)

    for i in graph[idx]:
        if not visited[i]:
            visited[i] = True
            solutions(n + 1, i)
            visited[i] = False

for i in range(N):
    visited[i] = True
    solutions(0, i)
    visited[i] = False
print(0)