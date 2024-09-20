import collections
import sys
input = sys.stdin.readline
def bfs(v, group):
    queue = collections.deque([(v, group)])
    while queue:
        v, group = queue.popleft()
        visited[v] = group
        for i in graph[v]:
            if not visited[i]:
                queue.append((i, -group))
            if visited[i] == visited[v]:
                return False
    return True


for T in range(int(input())):
    u, v = map(int, input().split())
    graph = [[] for _ in range(u + 1)]
    for _ in range(v):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)

    visited = [False for _ in range(u + 1)]
    for i in range(1, u + 1):
        if not visited[i]:
            answer = bfs(i, 1)
            if not answer:
                break
    print("YES" if answer else "NO")