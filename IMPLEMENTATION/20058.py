import collections
N, Q = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(2 ** N)]
L = list(map(int, input().split()))
steps = [(1, 0), (0, 1), (-1, 0), (0, -1)]

def solutions(n, graph):
    visited = [[False] * n for _ in range(n)]
    queue = collections.deque()
    answer = 0
    max_lump = 0
    for i in range(n):
        for j in range(n):
            if not visited[i][j] and graph[i][j] != 0:
                lump = 1
                visited[i][j] = True
                answer += graph[i][j]
                queue.append((i, j))
                while queue:
                    x, y = queue.popleft()
                    for step in steps:
                        nx, ny =  x + step[0], y+ step[1]
                        if 0 <= nx < n and 0 <= ny < n and not visited[nx][ny] and graph[nx][ny] != 0:
                            answer += graph[nx][ny]
                            visited[nx][ny] = True
                            queue.append((nx, ny))
                            lump += 1
                max_lump = max(max_lump, lump)
    return answer, max_lump

# 3칸이상 인접하지 않으면 graph[x][y] -= 1
def rotate_and_melt(i, j, size):
    tmp = [[0] * size for _ in range(size)]
    for x in range(i, i + size):
        for y in range(j, j + size):
            tmp[x - i][y - j] = graph[x][y]
    if i == 0 and j == 0:
        for x in range(size):
            for y in range(size):
                graph[x][y] = tmp[size - 1 - y][x]
    elif i == 0 and j:
        for x in range(size):
            for y in range(size):
                graph[x + i][y + j] = tmp[size - 1 - y][x]
    elif j == 0 and i:
        for x in range(size):
            for y in range(size):
                graph[x + i][y + j] = tmp[size - 1 - y][x]
    else:
        for x in range(size):
            for y in range(size):
                graph[x + i][y + j] = tmp[size - 1 - y][x]


for l in L:
    for i in range(0, 2 ** N, 2 ** l):
        for j in range(0, 2 ** N, 2 ** l):
            rotate_and_melt(i, j, 2 ** l)
    melting_ice_list = []
    for x in range(2 ** N):
        for y in range(2 ** N):
            melting_ice = 0
            for step in steps:
                nx, ny = x + step[0], y + step[1]
                if 0 <= nx < 2 ** N and 0 <= ny < 2 ** N and graph[nx][ny] > 0:
                    melting_ice += 1
            if melting_ice < 3 and graph[x][y]:
                melting_ice_list.append((x, y))
    for x, y in melting_ice_list:
        graph[x][y] -= 1
print(*solutions(2 ** N, graph), sep="\n")