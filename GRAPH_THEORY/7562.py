from collections import deque

steps = [(-1, 2), (-1, -2), (1, 2), (1, -2), (-2, 1), (-2, -1), (2, 1), (2, -1)]

def bfs(x, y):
    queue = deque()
    queue.append((x, y, cnt))
    visited[x][y] = True
    while queue:
        x, y, c = queue.popleft()
        if x == destination_x and y == destination_y:
            return c
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < chess_len and 0 <= ny < chess_len and not visited[nx][ny]:
                visited[nx][ny] = True
                queue.append((nx, ny, c + 1))

for T in range(int(input())):
    chess_len = int(input())
    knight_x, knight_y = map(int, input().split())
    destination_x, destination_y = map(int, input().split())
    visited = [[False] * chess_len for _ in range(chess_len)]
    cnt = 0
    print(bfs(knight_x, knight_y))
# 원래는 not in 을 사용해서 해보려 했는데 O(n)시간복잡도를 먹어가지고 시간 초과가 뜬다;; 고려하면서 코드를 짜야겠다
# from collections import deque
# 
# steps = [(-1, 2), (-1, -2), (1, 2), (1, -2), (-2, 1), (-2, -1), (2, 1), (2, -1)]
# t = int(input())
# def bfs(x, y):
#     visited = []
#     cnt = 0
#     queue = deque()
#     visited.append((x, y))
#     queue.append((x, y, cnt))
#     while queue:
#         x, y, c = queue.popleft()
#         if x == destination_x and y == destination_y:
#             return c
#         for step in steps:
#             nx, ny = x + step[0], y + step[1]
#             if 0 <= nx < chess_len and 0 <= ny < chess_len and (nx, ny) not in visited:
#                 visited.append((nx, ny))
#                 queue.append((nx, ny, c + 1))
# for _ in range(t):
#     chess_len = int(input())
#     knight_x, knight_y = map(int, input().split())
#     destination_x, destination_y = map(int, input().split())
#     print(bfs(knight_x, knight_y))