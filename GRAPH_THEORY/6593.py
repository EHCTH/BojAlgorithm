import sys
import collections


def solutions(queue):
    while queue:
        t, x, y = queue.popleft()
        for step in steps:
            nt, nx, ny = t + step[0], x + step[1], y + step[2]
            if 0 <= nt < l and 0 <= nx < n and 0 <= ny < m and distance[nt][nx][ny] == -1:
                if graph[nt][nx][ny] == '.':
                    distance[nt][nx][ny] = distance[t][x][y] + 1
                    queue.append((nt, nx, ny))
                elif graph[nt][nx][ny] == 'E':
                    return f"Escaped in {distance[t][x][y] + 1} minute(s)."
    return "Trapped!"


while True:
    l, n, m = map(int, sys.stdin.readline().split())
    if l == 0 and n == 0 and m == 0:
        break
    graph = [[] * n for _ in range(l)]
    distance = [[[-1] * m for _ in range(n)] for _ in range(l)]
    queue = collections.deque()
    steps = ((0, 0, 1), (0, 0, -1), (0, 1, 0), (0, -1, 0), (1, 0, 0), (-1, 0, 0))
    for i in range(l):
        for _ in range(n):
            graph[i].append(list(map(str, sys.stdin.readline().strip())))
        sys.stdin.readline()
    for t in range(l):
        for i in range(n):
            for j in range(m):
                if graph[t][i][j] == 'S':
                    queue.append((t, i, j))
                    distance[t][i][j] = 0
    print(solutions(queue))