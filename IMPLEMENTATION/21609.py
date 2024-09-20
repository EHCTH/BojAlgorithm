import collections

n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
answer = 0
steps = [(1, 0), (0, 1), (-1, 0), (0, -1)]


def search_block(i, j):
    queue = collections.deque()
    queue.append((i, j))
    choice = graph[i][j]
    normal_block = [[i, j]]
    rainbow_block = []
    while queue:
        x, y = queue.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < n and not visited[nx][ny]:
                if graph[nx][ny] == choice:
                    visited[nx][ny] = True
                    queue.append((nx, ny))
                    normal_block.append([nx, ny])
                elif graph[nx][ny] == 0:
                    queue.append((nx, ny))
                    visited[nx][ny] = True
                    rainbow_block.append([nx, ny])
    for x, y in rainbow_block:
        visited[x][y] = False
    return [len(normal_block + rainbow_block), len(rainbow_block), normal_block + rainbow_block]

def counter_clockwise(graph):
    tmp = [[0] * n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            tmp[i][j] = graph[j][n - 1 - i]
    return tmp


def gravity(graph):
    for column in range(n):
        tmp = [-2] * n
        idx = n - 1
        for i in range(n - 1, -1, -1):
            if graph[i][column] == -1:
                idx = i
                tmp[idx] = graph[i][column]
                idx -= 1
            elif graph[i][column] == -2:
                continue
            else:
                tmp[idx] = graph[i][column]
                idx -= 1
        for i in range(n):
            graph[i][column] = tmp[i]
    return graph

def remove_block(groups):
    global answer
    answer += len(groups[2]) ** 2
    for x, y in groups[2]:
        graph[x][y] = -2


while True:
    visited = [[False] * n for _ in range(n)]
    groups = []
    for i in range(n):
        for j in range(n):
            if graph[i][j] >= 1 and not visited[i][j]:
                visited[i][j] = True
                group = search_block(i, j)
                if group[0] >= 2:
                    groups.append(group)
    if not groups:
        break
    groups.sort(reverse=True)
    remove_block(groups[0])
    gravity(graph)
    graph = gravity(gravity(counter_clockwise(graph)))
    # for i in graph:
    #     print(i)
    # exit(0)
    # [1, -1, -2, -2, -2]
    # [3, -2, 2, 2, 1]
    # [-1, -2, 1, 3, 2]
    # [-2, -2, 2, 1, 2]
    # [-2, 0, 2, -1, 2]

print(answer)