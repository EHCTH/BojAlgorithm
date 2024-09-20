# cctv1_step = [(0, 1)]
# cctv2_step = [(0, 1), (0, -1)]
# cctv3_step = [(-1, 0), (0, 1)]
# cctv4_step = [(-1, 0), (0, 1), (0, -1)]
# cctv5_step = [(1, 0), (0, 1), (0, -1), (-1, 0)]
# all_cctv_step = [[], [(0, 1)], [(0, 1), (0, -1)], [(-1, 0), (0, 1)], [(-1, 0), (0, 1), (0, -1)], [(1, 0), (0, 1), (0, -1), (-1, 0)]]
#
# n, m = map(int, input().split())
# graph = [list(map(int, input().split())) for _ in range(n)]
#
import collections

steps = [(-1, 0), (1, 0), (0, 1), (0, -1)]

def solutions(queue1, queue2):
    while queue1:
        x, y = queue1.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] != '#' and vistied1[nx][ny] == -1:
                vistied1[nx][ny] = vistied1[x][y] + 1
                queue1.append((nx, ny))
    while queue2:
        x, y = queue2.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                return vistied2[x][y] + 1
            if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] == '.' and vistied2[nx][ny] == -1:
                if (vistied1[nx][ny] > vistied2[x][y] + 1) or vistied1[nx][ny] == -1:
                    vistied2[nx][ny] = vistied2[x][y] + 1
                    queue2.append((nx, ny))
    return "IMPOSSIBLE"


for T in range(int(input())):
    m, n = map(int, input().split())
    vistied1 = [[-1] * m for _ in range(n)]
    vistied2 = [[-1] * m for _ in range(n)]
    queue1 = collections.deque()
    queue2 = collections.deque()
    graph = [list(input()) for _ in range(n)]
    for i in range(n):
        for j in range(m):
            if graph[i][j] == '*':
                vistied1[i][j] = 0
                queue1.append((i, j))
            elif graph[i][j] == '@':
                vistied2[i][j] = 0
                queue2.append((i, j))
    print(solutions(queue1, queue2))