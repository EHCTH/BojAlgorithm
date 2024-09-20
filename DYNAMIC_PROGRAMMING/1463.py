import collections
n = int(input())
queue = collections.deque([1])
dist = [-1] * (10 ** 6 + 1)
dist[1] = 0
while queue:
    x = queue.popleft()
    if x == n:
        print(dist[n])
        break
    else:
        for i in [x + 1, 2 * x, 3 * x]:
            if 1 <= i < 10 ** 6 + 1 and dist[i] == -1:
                dist[i] = dist[x] + 1
                queue.append(i)