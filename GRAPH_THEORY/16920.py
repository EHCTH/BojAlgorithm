import collections
n, m, p = map(int, input().split())
s = [0] + list(map(int, input().split()))
graph = [list(input()) for _ in range(n)]
answer = [0] + [0 for i in range(1, p + 1)]
steps = [(-1, 0), (1, 0), (0, -1), (0, 1)]
queue = [collections.deque() for _ in range(p + 1)]
for i in range(n):
    for j in range(m):
        if graph[i][j].isdigit():
            answer[int(graph[i][j])] += 1
            queue[int(graph[i][j])].append((i, j, 0))

def solutions():
    while True:
        is_Extend = False
        for player in range(1, p + 1):
            next_queue = collections.deque()
            while queue[player]:
                x, y, cur_step = queue[player].popleft()
                if cur_step >= s[player]:
                    next_queue.append((x, y, 0))
                    continue
                for step in steps:
                    nx, ny = x + step[0], y + step[1]
                    if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] == ".":
                        queue[player].append((nx, ny, cur_step + 1))
                        graph[nx][ny] = str(player)
                        answer[player] += 1
                        is_Extend = True
            queue[player] = next_queue
        if not is_Extend:
            break
solutions()
print(*answer[1:])