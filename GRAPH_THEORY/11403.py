from collections import deque

n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]
visited = [[0] * n for _ in range(n)]

def bfs(k):
    check = [0 for _ in range(n)]
    queue = deque()
    queue.append(k)
    while queue:
        i = queue.popleft()
        for j in range(n):
            if check[j] == 0 and graph[i][j] == 1:
                queue.append(j)
                check[j] = 1
                visited[k][j] = 1
for i in range(n):
    bfs(i)
for i in visited:
    print(*i)