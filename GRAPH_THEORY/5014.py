from collections import deque

F, S, G, U, D = map(int, input().split())
floor = [0 for _ in range(F + 1)]
visited = [False for _ in range(F + 1)]


def bfs(S):
    if S == G:
        return True
    queue = deque()
    queue.append(S)
    visited[S] = True
    while queue:
        s = queue.popleft()
        if s == G:
            return floor[s]
        for i in [s + U, s - D]:
            if 1 <= i < len(floor) and not visited[i]:
                floor[i] = floor[s] + 1
                visited[i] = True
                queue.append(i)
answer = bfs(S)
if answer:
    print(floor[G])
else:
    print("use the stairs")