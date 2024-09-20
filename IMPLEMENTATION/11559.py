import collections

n, m = 12, 6
graph = [list(input()) for _ in range(n)]
steps = [(-1, 0), (0, 1), (0, -1), (1, 0)]
def destroy(data):
    index_num = [False] * m
    for x, y in data:
        graph[x][y] = '.'
        index_num[y] = True
    for i in range(m):
        if index_num[i]:
            tmp = ['.'] * n
            idx = n - 1
            for j in range(n - 1, -1, -1):
                if graph[j][i] == '.':
                    continue
                tmp[idx] = graph[j][i]
                idx -= 1
            for j in range(len(tmp)):
                graph[j][i] = tmp[j]



def solutions(color, i, j, visited):
    queue = collections.deque()
    queue.append((i, j))
    destroy_data = [(i, j)]
    cnt = 1
    while queue:
        x, y = queue.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m and not visited[nx][ny] and graph[nx][ny] == color:
                queue.append((nx, ny))
                visited[nx][ny] = True
                destroy_data.append((nx, ny))
                cnt += 1
    if cnt >= 4:
        destroy(destroy_data)
        return True


answer = 0
while True:
    is_bool = False
    visited = [[False] * m for _ in range(n)]
    for i in range(n):
        for j in range(m):
            if not visited[i][j]:
                visited[i][j] = True
                if graph[i][j] != '.':
                    if solutions(graph[i][j], i, j, visited):
                        is_bool = True
    if not is_bool:
        print(answer)
        break
    answer += 1