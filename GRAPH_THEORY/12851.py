import collections

n, k = map(int, input().split())
distance = [-1] * (100001)
cnt = 0

def bfs(num):
    global cnt
    queue = collections.deque()
    queue.append((num, 0))
    distance[num] = 0
    t = 0
    while queue:
        num, time = queue.popleft()
        if num == k and t == time:
            cnt += 1
        if not cnt and num == k:
            cnt += 1
            t = time
        else:
            for next in [num * 2, num - 1, num + 1]:
                if 0 <= next < 100001 and (distance[next] == -1 or distance[next] == time + 1):
                    distance[next] = distance[num] + 1
                    queue.append((next, time + 1))
    return distance[k]

print(bfs(n))
print(cnt)