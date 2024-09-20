import collections
n = int(input())
queue = collections.deque([i + 1 for i in range(n)])
while queue:
    x = queue.popleft()
    if not queue:
        print(x)
        break
    next_queue = queue.popleft()
    queue.append(next_queue)