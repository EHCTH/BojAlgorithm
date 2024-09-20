import collections
def solutions(x, y):
    queue = collections.deque()
    queue.append((x, y))
    visited[x][y] = True
    answer = 0
    which_data = []
    while queue:
        x, y = queue.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n + 2 and 0 <= ny < m + 2 and graph[nx][ny] != '*' and not visited[nx][ny]:
                if graph[nx][ny] == '.':
                    visited[nx][ny] = True
                    queue.append((nx, ny))
                elif graph[nx][ny].islower():
                    key_data.append((graph[nx][ny]))
                    visited[nx][ny] = True
                    queue.append((nx, ny))
                    remove_data = []
                    for a, b in which_data:
                        if chr(ord(graph[a][b]) + 32) in key_data:
                            queue.append((a, b))
                            remove_data.append((a, b))
                    for a, b in remove_data:
                        which_data.remove((a, b))

                elif graph[nx][ny].isupper():
                    if chr(ord(graph[nx][ny]) + 32) in key_data:
                        visited[nx][ny] = True
                        queue.append((nx, ny))
                    else:
                        visited[nx][ny] = True
                        which_data.append((nx, ny))
                else:
                    answer += 1
                    graph[nx][ny] = "#"
                    visited[nx][ny] = True
                    queue.append((nx, ny))
    return answer

for T in range(int(input())):
    n, m = map(int, input().split())
    # 대문자에 32를 더하면 소문자
    graph = [["."] * (m + 2) for _ in range(n + 2)]
    visited = [[False] * (m + 2) for _ in range(n + 2)]
    steps = [(-1, 0), (1, 0), (0, 1), (0, -1)]
    for i in range(1, n + 1):
        graph[i][1: m + 1] = list(input())
    key_data = list(input())
    print(solutions(0, 0))