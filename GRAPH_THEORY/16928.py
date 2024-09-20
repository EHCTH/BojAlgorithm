import collections

n, m = map(int, input().split())
ladder = [[] for _ in range(101)]
snake = [[] for _ in range(101)]
dice = 7  # 1 ~ 6
queue = collections.deque()
queue.append((1, 0))
answer = 1e9
for _ in range(n):
    a, b = map(int, input().split())
    ladder[a].append(b)

for _ in range(m):
    a, b = map(int, input().split())
    snake[a].append(b)
visited = [False] * 1001


def solutions(queue):
    global answer
    while queue:
        x, cnt = queue.popleft()
        if x == 100:
            return cnt
        for i in range(1, dice):
            next_x = x + i
            if 1 <= next_x <= 100 and not visited[next_x]:
                if ladder[next_x]:
                    next_x = ladder[next_x][0]
                if snake[next_x]:
                    next_x = snake[next_x][0]
                if not visited[next_x]:
                    visited[next_x] = True
                    queue.append((next_x, cnt + 1))

print(solutions(queue))