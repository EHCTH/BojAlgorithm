from collections import deque
import sys
input = sys.stdin.readline
n, m, k, x = map(int, input().split())
array = [[] for _ in range(n + 1)]  # type: ignore
for _ in range(m):
    a, b = map(int, input().split())
    array[a].append(b)
distance = [-1] * (n + 1)

queue = deque()  # type: ignore
queue.append(x)
distance[x] = 0
while queue:
    v = queue.popleft()
    for i in array[v]:
        if distance[i] == -1:
            distance[i] = distance[v] + 1
            queue.append(i)
check = False
for i in range(1, len(distance)):
    if distance[i] == k:
        print(i, end=" ")
        check = True
if not check:
    print(-1)