import collections
n, m = 5, 5
graph = [[list(map(int, input().split())) for _ in range(n)] for _ in range(n)]
min_num = 1e9
new_graph = [[] for _ in range(n)]
steps = [(0, -1, 0), (0, 1, 0), (1, 0, 0), (0, 0, -1), (0, 0, 1), (-1, 0, 0)]
visited = [False] * 5

def permutation(n, N, visited, answer):
    result = []
    if n == N:
        result.append(answer)
        return result
    for i in range(N):
        if not visited[i]:
            visited[i] = True
            result += permutation(n + 1, N, visited,  answer + [i])
            visited[i] = False
    return result
a = permutation(0, 5, visited, [])



def rotate(new_graph):
    copy_graph = [[0] * m for _ in range(n)]
    for i in range(m):
        for j in range(n):
            copy_graph[i][j] = new_graph[n - 1 - j][i]
    for i in range(n):
        for j in range(m):
            new_graph[i][j] = copy_graph[i][j]


def search(z, x, y, new_graph):
    visited = [[[-1] * m for _ in range(n)] for _ in range(n)]
    visited[0][0][0] = 0
    queue = collections.deque()
    queue.append((z, x, y))
    while queue:
        z, x, y = queue.popleft()
        if z == n - 1 and x == n - 1 and y == m - 1:
            return visited[z][x][y]
        for step in steps:
            nz, nx, ny = z + step[0], x + step[1], y + step[2]
            if 0 <= nz < n and 0 <= nx < n and 0 <= ny < m:
                if new_graph[nz][nx][ny] and visited[nz][nx][ny] == -1:
                    visited[nz][nx][ny] = visited[z][x][y] + 1
                    queue.append((nz, nx, ny))
    return 1e9


def solutions(cnt, graph, new_graph):
    global min_num
    if cnt == 5:
        visited = [False] * n
        a = permutation(0, 5, visited, [])
        for i in a:
            copy_graph = []
            for j in i:
                copy_graph.append(new_graph[j])
            if copy_graph[0][0][0]:
                answer = search(0, 0, 0, copy_graph)
                min_num = min(min_num, answer)
        return
    for i in range(4):
        new_graph[cnt] = [item[::] for item in graph[cnt]]
        while i:
            i -= 1
            rotate(new_graph[cnt])
        solutions(cnt + 1, graph, new_graph)


solutions(0, graph, new_graph)
if min_num == 1e9:
    print(-1)
else:
    print(min_num)