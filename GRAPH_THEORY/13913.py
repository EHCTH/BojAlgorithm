import collections
n, m = map(int, input().split())
queue = collections.deque([n])
next_move = [0] * (100000 + 1)
distance = [-1] * (100000 + 1)
distance[n] = 0
while queue:
    x = queue.popleft()
    for i in [x + 1, x - 1, x * 2]:
        if 0 <= i < 100001 and distance[i] == -1:
            distance[i] = distance[x] + 1
            next_move[i] = x
            queue.append(i)
print(distance[m])

cur = m
answer = []
while True:
    answer.append(cur)
    if cur == n:
        break
    cur = next_move[cur]
print(*answer[::-1])