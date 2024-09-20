import collections

queue = collections.deque()
queue.append((1, 1))
n = int(input())
graph = [[0] * (n + 2) for _ in range(n + 2)]
snake = 1
apple = 2
steps = {0: (1, 0), 1: (0, 1), 2: (-1, 0), 3: (0, -1)}
# D = +3
# L = + 1
dir = 1
next_move = []
for k in range(int(input())):
    a, b = map(int, input().split())
    graph[a][b] = apple

for mv in range(int(input())):
    next_move.append(list(input().split()))
next_move.sort(key=lambda x: -int(x[0]))
tails = [(1, 1)]
graph[1][1] = snake
def solutions(queue, dir, next_move):
    cnt = 0
    x, y = 1, 1
    while queue:
        if next_move:
            if cnt == int(next_move[-1][0]):
                a, b = next_move.pop()
                if b == 'L':
                    dir += 1
                else:
                    dir += 3
        dir %= 4
        a, b = steps[dir][0], steps[dir][1]
        nx, ny = x + a, y + b
        if (nx < 1 or nx > n or ny < 1 or ny > n) or graph[nx][ny] == snake:
            return cnt + 1
        if graph[nx][ny] != apple:
            a, b = tails.pop(0)
            graph[a][b] = 0
        graph[nx][ny] = snake
        tails.append((nx, ny))
        x, y = nx, ny
        cnt += 1
print(solutions(queue, dir, next_move))